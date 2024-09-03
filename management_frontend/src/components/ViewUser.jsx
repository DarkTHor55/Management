import React, { useState } from 'react';

const ViewUser = () => {
    const [userId, setUserId] = useState('');
    const [userDetails, setUserDetails] = useState(null);

    const handleViewUser = () => {
        fetch(`http://localhost:8085/api/v1/users/management/${userId}`)
            .then(response => response.json())
            .then(data => setUserDetails(data))
            .catch(error => console.error('Error:', error));
    };

    return (
        <div className="view-user">
            <h3>View User</h3>
            <input
                type="text"
                placeholder="Enter User ID"
                value={userId}
                onChange={(e) => setUserId(e.target.value)}
            />
            <button onClick={handleViewUser}>View User</button>
            {userDetails && (
                <div className="user-details">
                    <p>Name: {userDetails.name}</p>
                    <p>Email: {userDetails.email}</p>
                    <p>Role: {userDetails.role}</p>
                    {/* Display other user details here */}
                </div>
            )}
        </div>
    );
};

export default ViewUser;
