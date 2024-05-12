import React, { useState } from 'react';
import Swal from 'sweetalert2';
import { Link } from 'react-router-dom';

function SignupForm() {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [role, setRole] = useState('');

  const handleSignup = () => {
    if (username.trim() === '' || email.trim() === '' || password.trim() === '' || confirmPassword.trim() === '') {
        Swal.fire({
          icon: 'error',
          title: '',
          text: 'Please Fill All fields'
        });
        return;
      }

      if (role.trim() === '') {
        Swal.fire({
          icon: 'error',
          title: '',
          text: 'Please Select Role'
        });
        return;
      }

    if (password !== confirmPassword) {
        Swal.fire({
          icon: 'error',
          title: '',
          text: 'Passwords do not match'
        });
        return;
      }

      if (!email.includes('@')) {
        Swal.fire({
          icon: 'error',
          title: '',
          text: 'Please Check Email'
        });
        return;
      }

    fetch('http://localhost:8040/api/v1/socialApp/auth/signup', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body:JSON.stringify({
        username: username,
        email: email,
        password: password,
        role: [role]
      })
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Signup failed');
      }
      return response.json();
    })
    .then(data => {
        Swal.fire({
            icon: 'success',
            title: '',
            text: 'Registerd!'
        }).then(() => {
            window.location.href = '/';
          });
    })
    .catch(error => {
      Swal.fire({
        icon: 'error',
        title: '',
        text: 'Error signing up'
      });
    });
  };

  return (
    <div className="App">
      <header className="App-header">
        <div className='border p-4' style={{ width: '300px' }}>
          <p>Signup Form</p>
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
              type="text"
              className="form-control"
              placeholder='Email'
              value={email}
              onChange={(e) => setEmail(e.target.value)}
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
          <div className="mb-3 row">
            <input
              type="password"
              className="form-control"
              placeholder='Confirm Password'
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
            />
          </div>
          <div className="mb-3 row">
  <select className="form-control" value={role} onChange={(e) => setRole(e.target.value)}>
    <option value="" disabled selected>Select Role</option>
        <option value="admin">Admin</option>
              <option value="user">User</option>
  </select>
</div>
          <button className="btn btn-success" onClick={handleSignup}>Signup</button>
          <Link to="/" className="btn btn-warning ms-2">Cancel</Link>
        </div>
      </header>
    </div>
  );
}

export default SignupForm;
