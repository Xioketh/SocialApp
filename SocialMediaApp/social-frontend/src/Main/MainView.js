import React, { useState, useEffect  } from 'react';
import Swal from 'sweetalert2';
import { Link } from 'react-router-dom';
import './MainView.css';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Form from 'react-bootstrap/Form';

function MainView() {
  const [posts, setPosts] = useState([]);
  const [editPost, setEditPosts] = useState([]);
  const [showAddPostForm, setShowAddPostForm] = useState(false);
  const [showAllPosts, setShowAllPosts] = useState(false); 
  const [disablePostSaveBtn, setDisablePostSaveBtn] = useState(false); 
  
  const [modalShow, setModalShow] = React.useState(false)

  const [description, setDescription] = useState(''); 
  const [test, setTest] = useState(''); 
  const [title, setTitle] = useState('');
  const [imageUrl, setImageUrl] = useState([]);
  const [imageFiles, setImageFiles] = useState([]);
  const [imgAddingCount, setImgAddingCount] = useState([]);

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

const handleAddPostsClick = () =>{
    setShowAddPostForm(true);
    setShowAllPosts(false); 
}

const handleImageChange = (e) => {
  const files = e.target.files[0];
  setImageFiles(prevImageFiles => [...prevImageFiles, files]);
};

const handleSaveImages = async () => {
  

    if(description.trim() === '' ||title.trim() === ''){
Swal.fire(
  '',
  'Please Fill Empty Fields',
  'warning'
)
    }else if(imageFiles.length == 0){
      Swal.fire(
        '',
        'Please Add Image(s)',
        'warning'
      )
    }else{
      try {
        setDisablePostSaveBtn(true);
      const formData = new FormData();
    imageFiles.forEach(file => {
      formData.append('files', file);
    });

    const token = sessionStorage.getItem('token');
    const response = await fetch("http://localhost:8040/api/v1/socialApp/post/uploadImages", {
      method: "POST",
      headers: {
        'Authorization': `Bearer ${token}`
      },
      body: formData
    });

    if (!response.ok) {
      throw new Error('Image Save Failed');
    }

    console.log('Images saved successfully');

    const data = await response.json();
    console.log(data);

    handleAddPostSubmit(data);
  } catch (error) {
    setDisablePostSaveBtn(false);
    console.error('Error:', error);
  }
    }
    
};


const handleAddPostSubmit = (data) => {
    const token = sessionStorage.getItem('token');
    const userName = sessionStorage.getItem('username');

    const imageUrlArray = data.map(url => url.trim()).filter(url => url !== "");
    fetch('http://localhost:8040/api/v1/socialApp/post/create', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({
            userId: sessionStorage.getItem('userCode'),
            description: description,
            title:title,
            likeCount: 0,
            imageUrl: imageUrlArray,
            username: userName
        })
      })
      .then(response => {
        console.log('Server Response:', response);
        if (!response.ok) {
          throw new Error(' failed');
        }
        return response.json();
    })
      .then(data => {
        Swal.fire({
            icon: 'success',
            title: '',
            text: 'Post Saved!'
        }).then(() => {
            setDescription('');
            setTitle('');
            setImageUrl([]);
            setImageFiles([]);
            setImgAddingCount([]);
            handleAllPostsClick();
            setDisablePostSaveBtn(false);
          });
     
      })
      .catch(error => {
        console.error('Error:', error);
        Swal.fire({
          icon: 'error',
          title: '',
          text: ''
        });
      });
}

  const handleAllPostsClick = () => {
    const token = sessionStorage.getItem('token');
    setPosts([])
    setShowAddPostForm(false);
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

  const handleDeletePost = (post) => {
    Swal.fire({
      title: '',
      text: 'Are you sure to Delete?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Yes',
      cancelButtonText: 'No',
    }).then((result) => {
      if(result.isConfirmed){
        fetch(`http://localhost:8040/api/v1/socialApp/post/delete/${post.id}`, {
          method: 'post',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${sessionStorage.getItem('token')}`
          }
        })
        .then(response => {
          if (!response.ok) {
            throw new Error('Failed to delete post');
          }
    
          Swal.fire({
            icon: 'success',
            title: '',
            text: 'Post Deleted!'
        }).then(() => {
          handleAllPostsClick();
          });
        
        })
        .catch(error => {
          console.error('Error deleting post:', error);
        });
      }
    });
  };
  
  const handleEditPost = (post) => {
    setEditPosts(post)
    console.log(post)
    setModalShow(true);
  };

  function MyVerticallyCenteredModal(props) {
    const [editPostTitle, setEditPostTitle] = useState('');
    const [editPostDescription, setEditPostDescription] = useState('');
  
    const handleSaveChanges = (e) => {
      e.preventDefault();

    const token = sessionStorage.getItem('token');
    fetch('http://localhost:8040/api/v1/socialApp/post/updatePost', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({
          id:editPost.id,
            userId: editPost.id,
            description: editPostDescription ? editPostDescription : editPost.description,
            title:editPostTitle ? editPostTitle : editPost.title ,
            likeCount: editPost.likeCount,
            imageUrl: editPost.imageUrl,
            username: editPost.username
        })
      })
      .then(response => {
        console.log('Server Response:', response);
        if (!response.ok) {
          throw new Error(' failed');
        }
        return response.json();
    })
      .then(data => {
        Swal.fire({
            icon: 'success',
            title: '',
            text: 'Post Updated!'
        }).then(() => {
            setEditPostDescription('');
            setEditPostTitle('');
            handleAllPostsClick();
            props.onHide();
          });
     
      })
      .catch(error => {
        console.error('Error:', error);
        Swal.fire({
          icon: 'error',
          title: '',
          text: ''
        });
      });

      
    };
  
    return (
      <Modal {...props} size="lg" aria-labelledby="contained-modal-title-vcenter" centered>
        <Modal.Header closeButton>
          <Modal.Title>{editPost.title}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleSaveChanges}>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Post Title</Form.Label>
              <Form.Control
                type="text"
                placeholder={editPost.title}
                onChange={(e) => setEditPostTitle(e.target.value)}
                autoFocus
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
              <Form.Label>Post Description</Form.Label>
              <Form.Control
                as="textarea"
                rows={3}
                placeholder={editPost.description}
                onChange={(e) => setEditPostDescription(e.target.value)}
              />
            </Form.Group>
            <Button variant="success" type="submit">
              Save Changes
            </Button>
          </Form>
        </Modal.Body>
      </Modal>
    );
  }
  


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
          <button className="nav-link text-white" aria-current="page" onClick={handleAllPostsClick}>
            All Posts
          </button>
        </li>
        <li className="nav-item">
        <button className="nav-link text-white" aria-current="page" onClick={handleAddPostsClick}>
            Add Post
          </button>
        </li>
        <li className="nav-item">
          <button className="nav-link text-danger" onClick={handleLogoutClick}>LogOut</button>
        </li>
      </ul>

      {showAddPostForm && (
  <div className="d-flex justify-content-center">
    <form id="addPostForm" className="p-5 border mt-5" style={{ width: "50%" }}>
      <h4>Add Post</h4>

      <label htmlFor="title">Title:</label><br/>
      <input type="text" id="title" name="title" required className="form-control" value={title}
        onChange={(e) => setTitle(e.target.value)}/><br/>

      <label htmlFor="description">Description:</label><br/>
      <textarea id="description" name="description" required className="form-control" value={description}
        onChange={(e) => setDescription(e.target.value)}></textarea><br/>
<div style={{ display: "none" }}>
<input type="text" id="test" name="test" required className="form-control" value={test}
        onChange={(e) => setTest(e.target.value)}/><br/>
</div>

<label htmlFor="numImages">Number of Images:</label><br/>
<input type="number" id="numImages" name="numImages" min="1" required className="form-control" 
  onChange={(e) => {
    const numImages = parseInt(e.target.value);
    setImgAddingCount([...Array(numImages).keys()].map(i => ""));
  }}
/><br/>

{[...Array(imgAddingCount.length).keys()].map((_, index) => (
  <div key={index}>
    <form encType="multipart/form-data">
    <label htmlFor={`image${index + 1}`}>Image {index + 1}:</label><br/>
    <input type="file" accept="image/*" id={`image${index + 1}`} required className="form-control" onChange={(e) => handleImageChange(e)} />
    <br/>
    </form>
  </div>
))}
{/* here */}
      <button type="submit" className='btn btn-success' onClick={handleSaveImages} disabled={disablePostSaveBtn}>Save</button>
    </form>
  </div>
)}




{showAllPosts && (
  <div className="container justify-content-center">
  {chunkedPosts.map((chunk, index) => (
    <div key={index} className="row">
      {chunk.map(post => (
        <div key={post.id} className='card-header col-3 p-3 border m-4'>
          <h5 class="text-center">{post.title}</h5>
          <div className="btn-group float-end mt-2" role="group">
          <i class="fa fa-pen " style={{ color: 'blue' }} onClick={() => handleEditPost(post)}></i>
          <i className="fas fa-trash ms-2" style={{ color: 'red' }} onClick={() => handleDeletePost(post)}></i>
          </div>
          <div className='p-1'>
            {post.imageUrl.map((pimg, index) => (
              <img key={index} src={pimg} alt={`Image ${index}`} style={{ width: '100px', height:'70px' }} className='m-1' />
            ))}
          </div>
          <p><label style={{ fontWeight: 'bold' }}>Description:</label> {post.description}</p>
          <p><label style={{ fontWeight: 'bold' }}>Like:</label> {post.likeCount}</p>
          {/* <p class="text-primary text-end" style={{ fontSize: '12px' }}>Uploaded By: {post.username}</p> */}
        </div>
      ))}
    </div>
  ))}
</div>
)}

<MyVerticallyCenteredModal
        show={modalShow}
        onHide={() => setModalShow(false)}
      />


    </div>
  );
}

export default MainView;
