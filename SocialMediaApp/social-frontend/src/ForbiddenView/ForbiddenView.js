import React, { useState } from 'react';

function ForbiddenView() {
 

  return (
    <div className='d-flex justify-content-center align-items-center mt-5'>
  <div className='border p-5'>
    <h2 className='text-center mb-4 text-danger'>No Access!</h2>
  </div>
</div>
  );
}

export default ForbiddenView;
