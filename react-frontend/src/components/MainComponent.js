import React, { Component } from 'react';


class MainComponent extends Component {
   constructor(props) {
      super(props)
      this.register = this.register.bind(this);
      this.login = this.login.bind(this);

   }



   register() {
      this.props.history.push('/userRegister');
   }
   login() {
      this.props.history.push('/user/auth');
   }


   render() {
      return (
         <div>
            <h2>
               <div  >
                  <div className=" col-md-6 offset-md-3">
                     <button className="btn-btn-primary" onClick={this.register}>register</button>
                  </div>
               </div>
            </h2>
            <h2>
               <div className=" col-md-6 offset-md-3">
                  <div className="container">
                     <button className="btn-btn-primary" onClick={this.login}>login</button>
                  </div>
               </div>
            </h2>
         </div>
      );
   }
}

export default MainComponent;