import React from 'react';
import ProjectsService from '../services/ProjectsService';
import UserProjectsService from '../services/UserProjectsService';


class ProjectsComponent extends React.Component {

   constructor(props) {
      super(props)

      this.state = {
         name: '_',
         dateFrom: '1990-11-04T16:00:00',
         dateTo: '2050-11-04T16:00:00',
         projects: []
      }
      this.home = this.home.bind(this);
      this.search = this.search.bind(this);
      this.deleteProjects = this.deleteProjects.bind(this);
   }

   deleteProjects(id) {
      ProjectsService.projectsDelete(id).then(() => {
         this.setState({ projects: this.state.projects.filter(project => project.id !== id) });
      });
   }


   search = (e) => {
      e.preventDefault();

      let searchProjects = {
         name: this.state.name, dateFrom: this.state.dateFrom, dateTo: this.state.dateTo
      };



      console.log('searchProjects => ' + JSON.stringify(searchProjects));

      if (localStorage.getItem('type') === 'TEAM_MEMBER') {
         UserProjectsService.getProjects().then((response) => {
            this.setState({ projects: response.data })
         });
      } else {
         ProjectsService.getProjects(searchProjects).then((response) => {
            this.setState({ projects: response.data })
         });
      }
   }

   componentDidMount() {
      ProjectsService.getProjects().then((response) => {
         this.setState({ projects: response.data })
      });
   }
   home() {
      this.props.history.push('/user');
   }
   changeNameHandler = (event) => {
      this.setState({ name: event.target.value });
   }
   changeDateFromHandler = (event) => {
      this.setState({ dateFrom: event.target.value });
   }
   changeDateToHandler = (event) => {
      this.setState({ dateTo: event.target.value });
   }

   render() {
      return (
         <div>
            <h1 className="text-center">Projects</h1>
            <div className="row">
               <button className="btn-btn-primary" onClick={this.home}>Home</button>
            </div>
            <form >
               <libel> Name: </libel>
               <input placeholder="Name" name="name"
                  value={this.state.name} onChange={this.changeNameHandler} />

               <label> From: </label>
               <input type="datetime-local" name="dateFrom"
                  value={this.state.dateFrom} onChange={this.changeDateFromHandler} />

               <label> -- </label>
               <input type="datetime-local" name="dateTo"
                  value={this.state.dateTo} onChange={this.changeDateToHandler} />

               <button className="btn btn-success" onClick={this.search}> Search </button>
            </form>
            <table className="table table-striped">
               <thead>
                  <tr>

                     <td> Name</td>
                     <td> Date</td>
                     <td> Deadline</td>
                     <td> Hours</td>
                     <td> Delete</td>

                  </tr>
               </thead>
               <tbody>
                  {
                     this.state.projects.map(
                        project =>
                           <tr key={project.id}>
                              <td> {project.name}</td>
                              <td> {project.date}</td>
                              <td> {project.deadline}</td>
                              <td> {project.hours}</td>

                              <td><button style={{ margin: "10px" }} onClick={() => this.deleteProjects(project.id)} className="btn btn-danger">X</button></td>

                           </tr>
                     )
                  }
               </tbody>
            </table>
         </div>
      )
   }
}

export default ProjectsComponent