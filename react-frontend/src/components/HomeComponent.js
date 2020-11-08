import React, { Component, useLayoutEffect } from 'react';
import UserService from '../services/UserService';

class HomeComponent extends Component {


   constructor(props) {
      super(props)

      this.state = {
         user: ''
      }
      this.projects = this.projects.bind(this);
      this.logs = this.logs.bind(this);
      this.projectsUserAdd = this.projectsUserAdd.bind(this);

   }

   componentDidMount() {
      UserService.getUser().then((response) => {
         this.setState({ user: response.data })
      });
   }
   projects() {
      this.props.history.push('/projects');
   }
   logs() {
      this.props.history.push('/logs');
   }
   projectsUserAdd() {
      this.props.history.push('/projects/user/add');

   }


   render() {
      return (
         <div>
            <h1 key={this.state.user.id}>Welcome Mr. {this.state.user.name}  {this.state.user.surname}</h1>
            <h2>
               <button className="btn-btn-primary" onClick={this.projects}>projects</button>
            </h2>
            <h2>
               <button className="btn-btn-primary" onClick={this.logs}>logs</button>
            </h2>
            <h2>
               <button className="btn-btn-primary" onClick={this.projectsUserAdd}>add user to project</button>
            </h2>
         </div>
      );
   }
}

export default HomeComponent;