import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import HeaderComponent from './components/HeaderComponent';
import UserComponent from './components/UserComponent';
import RegisterComponent from './components/RegisterComponent';
import MainComponent from './components/MainComponent';
import LoginComponent from './components/LoginComponent';
import HomeComponent from './components/HomeComponent';
import SaveProjectsComponent from './components/SaveProjectsComponent';
import ProjectsComponent from './components/ProjectsComponent';
import LogsComponent from './components/LogsComponent';
import UserProjectsComponent from './components/UserProjectsComponent';




function App() {
  return (
    <div>
      <Router>
        <div className="container">
          <HeaderComponent />
          <div className="container">
            <Switch>
            {/* if({localStorage.getItem('type')}=='TEAM_MEMBER'){ */} 
              <Route path = "/users" component = {UserComponent}></Route>
              <Route path = "/logs" component = {LogsComponent}></Route>
              <Route path = "/projects/user/add" component = {UserProjectsComponent}></Route>
            <Route path = "/" exact component = {MainComponent}></Route>
            <Route path = "/user/auth" exact component = {LoginComponent}></Route>
            <Route path = "/userRegister" component = {RegisterComponent}></Route>
            <Route path = "/user" component = {HomeComponent}></Route>
            <Route path = "/project/save" component = {SaveProjectsComponent}></Route>
            <Route path = "/projects" component = {ProjectsComponent}></Route>

            </Switch>
          </div>
        </div>
      </Router>
    </div>

  );
}

export default App;
