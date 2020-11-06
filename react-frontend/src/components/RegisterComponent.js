import React, { Component } from 'react';
import UserService from '../services/UserService';

class RegisterComponent extends Component {

   constructor(props) {
      super(props)

      this.state = {
         // id: this.props.match.params.id,
         name: '',
         surname: '',
         password: '',
         email: '',
         type: ''
      }
      this.saveUser = this.saveUser.bind(this);
   }


   cancel() {
      this.props.history.push('/');
   }
   saveUser = (e) => {
      e.preventDefault();
      let user = {
         name: this.state.name, surname: this.state.surname, email: this.state.email,
         password: this.state.password, type: this.state.type
      };
      console.log('user => ' + JSON.stringify(user));

      UserService.register(user).then(res => {
         this.props.history.push('/users');
      });

   }
   changeNameHandler = (event) => {
      this.setState({ name: event.target.value });
   }
   changeSurnameHandler = (event) => {
      this.setState({ surname: event.target.value });
   }
   changeEmailHandler = (event) => {
      this.setState({ email: event.target.value });
   }
   changePasswordHandler = (event) => {
      this.setState({ password: event.target.value });
   }
   changeTypeHandler = (event) => {
      this.setState({ type: event.target.value });
   }


   render() {
      return (
         <div>
            <div className="container">
               <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{ marginLeft: "10px" }}>Cancel</button>
               <div className="rov">
                  <div className="card col-md-6 offset-md-3 offset-md-3">
                     <h3 className="text-center">Register</h3>
                     <div className="card-body">
                        <form >
                           <div className="form-grup">
                              <libel> Name: </libel>
                              <input placeholder="Name" name="name" className="form-control"
                                 value={this.state.name} onChange={this.changeNameHandler} />
                           </div>
                           <div className="form-group">
                              <label> Surname: </label>
                              <input placeholder="Surname" name="surname" className="form-control"
                                 value={this.state.surname} onChange={this.changeSurnameHandler} />
                           </div>
                           <div className="form-group">
                              <label> Email: </label>
                              <input placeholder="Email" name="email" className="form-control"
                                 value={this.state.email} onChange={this.changeEmailHandler} />
                           </div>
                           <div className="form-group">
                              <label> Password: </label>
                              <input name="password" className="form-control"
                                 value={this.state.password} onChange={this.changePasswordHandler} />
                           </div>
                           <div className="form-group">
                              <label> Type: </label>
                              <select defaultValue={'DEFAULT'} name="type" className="form-control" value={this.state.selectValue}
                                 onChange={this.changeTypeHandler} >
                                 <option value="DEFAULT" disabled>Choose a Project</option>
                                 <option value="TEAM_LEADER">TEAM_LEADER</option>
                                 <option value="TEAM_MEMBER">TEAM_MEMBER</option>
                              </select>
                           </div>

                           <button className="btn btn-success" onClick={this.saveUser}> Register </button>
                        </form>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      );
   }
}


export default RegisterComponent;