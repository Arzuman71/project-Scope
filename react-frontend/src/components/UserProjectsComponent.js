import React, { Component } from 'react';
import UserService from '../services/UserService';
import ProjectsService from '../services/ProjectsService';
import UserProjectsService from '../services/UserProjectsService';

class UserProjectsComponent extends Component {

   constructor(props) {
      super(props)

      this.state = {
         users: [],
         projects: [],
         userId: '',
         projectsId: ''
      }
      this.addUserToProject = this.addUserToProject.bind(this);
   }

   addUserToProject = (e) => {
      e.preventDefault();
      let projectsIdAndUserIdDto = {
         userId: this.state.userId, projectsId: this.state.projectsId
      };
      console.log('projectsIdAndUserIdDto => ' + JSON.stringify(projectsIdAndUserIdDto));

      UserProjectsService.addUserProjects(projectsIdAndUserIdDto).then(res => {
         this.props.history.push('/users');

      });

   }

   componentDidMount() {
      UserService.getUsers().then((response) => {
         this.setState({ users: response.data })
      });

      ProjectsService.getProjects().then((response) => {
         this.setState({ projects: response.data })
      });

   }
   changeProjectsIdHandler = (event) => {
      this.setState({ projectsId: event.target.value });
   }
   changeUserIdHandler = (event) => {
      this.setState({ userId: event.target.value });
   }



   render() {
      return (
         <div>
            <h1 className="text-center"> add user to project </h1>
            <form>
               <div className=" col-md-6 offset-md-3">
                  <select className="form-control" name="userId" value={this.state.selectValue}
                     onChange={this.changeUserIdHandler} >
                     {this.state.users.map(
                        user =>
                           <option value={user.id}>{user.name}</option>
                     )}
                  </select>
               </div>
               <div className=" col-md-6 offset-md-3">
                  <select className="form-control" name="projectsId" value={this.state.selectValue}
                     onChange={this.changeProjectsIdHandler}>
                     {this.state.projects.map(
                        project =>
                           <option value={project.id}>{project.name}</option>
                     )}
                  </select>
               </div>
               <button className="center offset-md-3 btn btn-success" onClick={this.addUserToProject}> Add </button>
            </form>

         </div>
      )
   }
}



export default UserProjectsComponent;