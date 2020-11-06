import React, { Component } from 'react';
import ProjectsService from '../services/ProjectsService';

class SaveProjectsComponent extends Component {

   constructor(props) {
      super(props)

      this.state = {
         // id: this.props.match.params.id,
         name: '',
         date: '',
         deadline: '',

      }
      this.saveProject = this.saveProject.bind(this);
   }


   cancel() {
      this.props.history.push('/');
   }
   saveProject = (e) => {
      e.preventDefault();
      let project = {
         name: this.state.name, date: this.state.date, deadline: this.state.deadline
      };
      console.log('project => ' + JSON.stringify(project));

      ProjectsService.saveProject(project).then(res => {
         this.props.history.push('/users');
      });

   }
   changeNameHandler = (event) => {
      this.setState({ name: event.target.value });
   }
   changeDateHandler = (event) => {
      this.setState({ date: event.target.value });
   }
   changeDeadlineHandler = (event) => {
      this.setState({ deadline: event.target.value });
   }



   render() {
      return (
         <div>
            <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{ marginLeft: "10px" }}>Cancel</button>
            <div className="container">
               <div className="rov">
                  <div className="card col-md-6 offset-md-3 offset-md-3">
                     <h3 className="text-center">Add Projects</h3>
                     <div className="card-body">
                        <form >
                           <div className="form-grup">
                              <libel> Name: </libel>
                              <input placeholder="Name" name="name" className="form-control"
                                 value={this.state.name} onChange={this.changeNameHandler} />
                           </div>
                           <div className="form-group">
                              <label> Date: </label>
                              <input type="datetime-local" placeholder="Date" name="date" className="form-control"
                                 value={this.state.date} onChange={this.changeDateHandler} />
                           </div>
                           <div className="form-group">
                              <label> Deadline: </label>
                              <input type="datetime-local" placeholder="Deadline" name="deadline" className="form-control"
                                 value={this.state.deadline} onChange={this.changeDeadlineHandler} />
                           </div>

                           <button className="btn btn-success" onClick={this.saveProject}> Save Project </button>
                        </form>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      );
   }
}


export default SaveProjectsComponent;