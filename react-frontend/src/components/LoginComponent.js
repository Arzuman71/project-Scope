import React, { Component } from 'react';
import UserService from '../services/UserService';

class LoginComponent extends Component {


   constructor(props) {
      super(props)

      this.state = {
         email: '',
         password: '',

      }
      this.loginUser = this.loginUser.bind(this);
   }
   getAuth() {
      return this.state.auth
   }

   cancel() {
      this.props.history.push('/');
   }
   loginUser = (e) => {
      e.preventDefault();
      let emailPass = {
         email: this.state.email, password: this.state.password,
      };
      console.log('user => ' + JSON.stringify(emailPass));

      UserService.login(emailPass).then(res => {
         this.props.history.push('/user');
      });


   }

   changeEmailHandler = (event) => {
      this.setState({ email: event.target.value });
   }
   changePasswordHandler = (event) => {
      this.setState({ password: event.target.value });
   }

   render() {
      return (
         <div>
            <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{ marginLeft: "10px" }}>Cancel</button>
            <div className="container">
               <div className="rov">
                  <div className="card col-md-6 offset-md-3 offset-md-3">
                     <h3 className="text-center">Login</h3>
                     <div className="card-body">
                        <form >

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
                           <button className="btn btn-success" onClick={this.loginUser}> Login </button>

                        </form>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      );
   }
}


export default LoginComponent;