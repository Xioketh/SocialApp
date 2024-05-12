import React, { useState } from 'react';
import Swal from 'sweetalert2';
import { Link } from 'react-router-dom';

function LoginForm() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = () => {
    fetch('http://localhost:8040/api/v1/socialApp/auth/signin', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        username: username,
        password: password
      })
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Login failed');
      }
      return response.json();
    })
    .then(data => {
      console.log('Response:', data);
      sessionStorage.clear();
      sessionStorage.setItem('userCode',data.userCode)
      sessionStorage.setItem('username',data.username)
      sessionStorage.setItem('token',data.token) 
      sessionStorage.setItem('role',data.role)

      if(data.role == 'ROLE_USER'){
        window.location.href = '/user';
      }else{
        window.location.href = '/main';
      }
      
    })
    .catch(error => {
      console.error('Error:', error);
      Swal.fire({
        icon: 'error',
        title: '',
        text: 'Invalid username or password'
      });
    });
  };

  return (
    <div className="App">
      <header className="App-header">
        <div className='border p-4' style={{ width: '300px' }}>
          <p>Login Form</p>
          <div className="mb-3 row">
            <input
              type="text"
              className="form-control"
              placeholder='User Name'
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </div>
          <div className="mb-3 row">
            <input
              type="password"
              className="form-control"
              placeholder='Password'
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <button className="btn btn-success" onClick={handleLogin}>Login</button>
          <Link to="/signup" className="btn btn-warning ms-2">SignUp</Link>
          {/* <button className="btn btn-warning ms-2">SignUp</button> */}
        </div>
      </header>
    </div>
  );
}

export default LoginForm;
