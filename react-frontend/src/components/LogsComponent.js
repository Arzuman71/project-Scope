import React, { Component } from 'react';
import LogService from '../services/LogService';
import UserProjectsService from '../services/UserProjectsService';

class LogsComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            projectId: '',
            hours: '',
            date: '',
            logs: [],
            projects: []
        }
        this.home = this.home.bind(this);
        this.deleteLog = this.deleteLog.bind(this);

    }
    deleteLog(id) {
        LogService.logDelete(id).then(() => {
            this.setState({ logs: this.state.logs.filter(log => log.id !== id) });
        });
    }


    home() {
        this.props.history.push('/user');
    }

    addLog = (e) => {
        e.preventDefault();
        let logDto = {
            hours: this.state.hours, date: this.state.date, projectId: this.state.projectId
        };
        console.log('logDto => ' + JSON.stringify(logDto));

        LogService.addLog(logDto).then(res => {
            this.props.history.push('/users');
        });

    }

    componentDidMount() {
        LogService.getLogs().then((response) => {
            this.setState({ logs: response.data })
        });
        UserProjectsService.getProjects().then((response) => {
            this.setState({ projects: response.data })
        });
    }


    changeProjectsIdHandler = (event) => {
        this.setState({ projectId: event.target.value });
    }
    changeHoursHandler = (event) => {
        this.setState({ hours: event.target.value });
    }
    changeDateHandler = (event) => {
        this.setState({ date: event.target.value });
    }


    render() {
        return (
            <div>
                <h1 className="text-center">My Logs</h1>
                <div className="row">
                    <button className="btn-btn-primary" onClick={this.home}>home</button>
                </div>
                <table className="table table-striped">
                    <thead>
                        <tr>

                            {/* <td> Picture</td> */}
                            <td> Date</td>
                            <td> Project name</td>
                            <td> Hours</td>
                            <td> Delete</td>
                        </tr>

                    </thead>
                    <tbody>
                        {
                            this.state.logs.map(
                                log =>
                                    <tr key={log.id}>
                                        {/* <td> {user.profilePicture}</td>    */}
                                        <td> {log.date}</td>
                                        <td> {log.projects.name}</td>
                                        <td> {log.hours}</td>
                                        <td><button style={{ margin: "10px" }} onClick={() => this.deleteLog(log.id)} className="btn btn-danger">X</button></td>

                                    </tr>
                            )
                        }
                    </tbody>
                </table>
                <h1 className="text-center">Add Log</h1>
                <form >
                    <div className="form-group">
                        <label> Hours: </label>
                        <input placeholder="hours" name="hours" className="form-control"
                            value={this.state.hours} onChange={this.changeHoursHandler} />
                    </div>
                    <div className="form-group">
                        <label> Date: </label>
                        <input type="datetime-local" name="date" className="form-control"
                            value={this.state.date} onChange={this.changeDateHandler} />
                    </div>
                    <div className="form-group">
                        <label> Projects: </label>
                        <select defaultValue={'DEFAULT'} className="form-control" name="projectId" value={this.state.selectValue}
                            onChange={this.changeProjectsIdHandler}>
                            <option value="DEFAULT" disabled>Choose a Project</option>
                            {this.state.projects.map(
                                project =>
                                    <option value={project.id}>{project.name}</option>
                            )}
                        </select>
                    </div>

                    <button className="btn btn-success" onClick={this.addLog}> Add log </button>
                </form>
            </div>
        )
    }
}

export default LogsComponent;