import React, { useState, useEffect  } from 'react';
import Swal from 'sweetalert2';
import './UserView.css';

function UserView() {
  const [posts, setPosts] = useState([]);
  const [userPosts, setUserPosts] = useState([]);
  const [showAllPosts, setShowAllPosts] = useState(false);

  useEffect(() => {
    handleAllPostsClick();

  }, []);

  const handleLogoutClick = () => {
    Swal.fire({
        title: '',
        text: 'Are you sure?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonText: 'Yes',
        cancelButtonText: 'No',
      }).then((result) => {
        if(result.isConfirmed){
            window.location.href = '/';
        }
       
      });
}

const handleLikeClick = (data) =>{
console.log(data);
fetch(`http://localhost:8040/api/v1/socialApp/likes/add-like`, {
          method: 'post',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${sessionStorage.getItem('token')}`
          },
          body: JSON.stringify({
            id: data.id,
            userCode: sessionStorage.getItem('userCode')
        })
        })
        .then(response => {
            handleAllPostsClick();
        })
        .catch(error => {
          console.error('Error Like post:', error);
        });
}


const handleUserLikedPosts =()=>{
    fetch(`http://localhost:8040/api/v1/socialApp/likes/getUserLikes`, {
          method: 'post',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${sessionStorage.getItem('token')}`
          },
          body: JSON.stringify({
            userCode: sessionStorage.getItem('userCode')
        })
        })
        .then(response => {
            if (!response.ok) {
              throw new Error('Failed to fetch posts');
            }
            return response.json();
          })
          .then(data => {
            setUserPosts(data); 

            // userPosts.some(userPost => userPost.postId === "8" && userPost.status === 'NOT_LIKE') ? console.log('found') : console.log('not found');


          })
          .catch(error => {
            console.error('Error fetching posts:', error);
          });
}

  const handleAllPostsClick = () => {
    const token = sessionStorage.getItem('token');
    setPosts([])
    setShowAllPosts(true); 
  
    fetch('http://localhost:8040/api/v1/socialApp/post/getAll', {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Failed to fetch posts');
      }
      return response.json();
    })
    .then(data => {
        console.log(data);
      setPosts(data); 
      handleUserLikedPosts();
    })
    .catch(error => {
      console.error('Error fetching posts:', error);
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Failed to fetch posts'
      });
    });
  };


  function chunkArray(array, size) {
    return array.reduce((acc, _, index) => {
      if (index % size === 0) {
        acc.push(array.slice(index, index + size));
      }
      return acc;
    }, []);
  }
  
  const chunkedPosts = chunkArray(posts, 3);
  

  return (
    <div>
      <ul className="nav justify-content-end bg-secondary" style={{ height: '50px' }}>
  <li className="nav-item">
    <span className="nav-link text-white">Hello {sessionStorage.getItem('username')}!</span>
  </li>
  <li className="nav-item">
    <button className="nav-link text-danger" onClick={handleLogoutClick}>LogOut</button>
  </li>
</ul>


      {showAllPosts && (
  <div className="container justify-content-center">
    {chunkedPosts.map((chunk, index) => (
      <div key={index} className="row">
        {chunk.map(post => (
          <div key={post.id} className='card-header col-3 p-3 border m-4'>
            <h5 className="text-center">{post.title}</h5>
            <div className="btn-group float-end mt-2" role="group">
            </div>
            <div className='p-1'>
              {post.imageUrl.map((pimg, index) => (
                <img key={index} src={pimg} alt={`Image ${index}`} style={{ width: '100px', height:'70px' }} className='m-1' />
              ))}
            </div>
            <p><label style={{ fontWeight: 'bold' }}>Description:</label> {post.description}</p>
            <p><label style={{ fontWeight: 'bold' }}>Like:</label> {post.likeCount}</p>
            <p className="text-primary text-end" style={{ fontSize: '12px' }}>Uploaded By: {post.username}</p>
            <hr />
               <div className="action group">
               {userPosts.some((userPost) => userPost.postId === post.id.toString() && userPost.status === 'NOT_LIKE') ? (
                 <button className="btn btn-primary p-1" onClick={() => handleLikeClick(post)}>Like</button>
               ) : (
                 userPosts.some((userPost) => userPost.postId === post.id.toString() && userPost.status === 'LIKE') ? (
                   <button className="btn btn-danger p-1" onClick={() => handleLikeClick(post)}>Dislike</button>
                 ) : (
                   <button className="btn btn-primary p-1" onClick={() => handleLikeClick(post)}>Like</button>
                 )
               )}
             </div>
            
          </div>
        ))}
      </div>
    ))}
  </div>
)}

    </div>
  );
}

export default UserView;
