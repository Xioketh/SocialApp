import React from 'react';
import './App.css';
import LoginForm from './Auth/LoginFrom';
import SignupForm from './Auth/SignupForm';
import {BrowserRouter as Router, Navigate, Route, Routes} from "react-router-dom";
import MainView from './Main/MainView';
import UserView from './UserView/UserView';
import ForbiddenView from "./ForbiddenView/ForbiddenView";

// const isAdmin = sessionStorage.getItem('role')? sessionStorage.getItem('role') ===;
function App() {
  // const navigate = useNavigate();

  // Function to check if user is admin
  // const checkIsAdmin = () => {
  //   if (!isAdmin) {
  //     navigate('/');
  //   }
  // };

  return (
    <Router>
    <Routes>
        <Route exact path="/" element={<LoginForm/>}/>
        <Route exact path="/signup" element={<SignupForm/>}/>
        <Route
        exact
        path="/main"
        element={sessionStorage.getItem('role') === 'ROLE_ADMIN' ? <MainView /> : <ForbiddenView/>}
      /> 
      <Route
        exact
        path="/user"
        element={sessionStorage.getItem('role') === 'ROLE_USER' ? <UserView /> : <ForbiddenView/>}
      /> 
  
    </Routes>
</Router>
  );
}

export default App;
