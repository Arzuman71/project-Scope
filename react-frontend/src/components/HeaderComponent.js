import React, { Component } from 'react';

class HeaderComponent extends Component {
   render() {
      return (
         <div>
            <header>
               <nav className = "navbar navbar-expand-md navbar-dark bg-dark">
               <div className="card col-md-6 offset-md-3 ">
               <h1  className="text-center">Full Stack</h1>
               </div>
               </nav>
            </header>
         </div>
      );
   }
}

export default HeaderComponent;