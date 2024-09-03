import React, { useState } from 'react';
import ViewUser from './ViewUser';
import UpdateUser from './UpdateUser';
import ListUsers from './ListUsers';

const UserManagement = () => {
    const [activeSection, setActiveSection] = useState('view');

    const renderSection = () => {
        switch (activeSection) {
            case 'view':
                return <ViewUser />;
            case 'update':
                return <UpdateUser />;
            case 'list':
                return <ListUsers />;
            default:
                return <ViewUser />;
        }
    };

    return (
        <div className="user-management-container">
            <nav className="user-management-nav">
                <button onClick={() => setActiveSection('view')}>View User</button>
                <button onClick={() => setActiveSection('update')}>Update User</button>
                <button onClick={() => setActiveSection('list')}>List Users</button>
            </nav>
            <div className="user-management-content">
                {renderSection()}
            </div>
        </div>
    );
};

export default UserManagement;
