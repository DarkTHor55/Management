import React, { useState } from 'react';

const UpdateUser = () => {
    const [userId, setUserId] = useState('');
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [role, setRole] = useState('');

    const handleUpdateUser = () => {
        const updatedUser = { name, email, role };
        fetch(`http://localhost:8085/api/v1/users/management/${userId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(updatedUser),
        })
            .then(response => response.json())
            .then(data => console.log('User updated:', data))
            .catch(error => console.error('Error:', error));
    };

    return (
        <div className="update-user">
            <h3>Update User</h3>
            <input
                type="text"
                placeholder="Enter User ID"
                value={userId}
                onChange={(e) => setUserId(e.target.value)}
            />
            <input
                type="text"
                placeholder="Name"
                value={name}
                onChange={(e) => setName(e.target.value)}
            />
            <input
                type="email"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
            />
            <select value={role} onChange={(e) => setRole(e.target.value)}>
                <option value="">Select Role</option>
                <option value="ADMINISTRATOR">Administrator</option>
                <option value="HR">HR</option>
                <option value="SALES">Sales</option>
                <option value="ACCOUNTANT">Accountant</option>
            </select>
            <button onClick={handleUpdateUser}>Update User</button>
        </div>
    );
};

export default UpdateUser;
