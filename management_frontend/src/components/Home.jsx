import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Homepage.css';

function Home() {
    const navigate = useNavigate();
    const userRole = localStorage.getItem('userRole'); // Retrieve user role from localStorage

    if (!userRole) {
        // Redirect if no userRole found in localStorage
        navigate('/access-denied');
        return null;
    }

    // Determine accessible links based on userRole
    const getNavLinks = () => {
        switch (userRole) {
            case 'ADMINISTRATOR':
                return (
                    <li><Link to="/profile">Profile</Link></li>
                );
            case 'HR':
                return (
                    <>
                        <li><Link to="/payroll">Payroll</Link></li>
                    </>
                );
            case 'ACCOUNTANT':
                return (
                    <>
                        <li><Link to="/read-billing">Bill</Link></li>
                        <li><Link to="/read-payroll">Payroll</Link></li>
                    </>
                );
            case 'SALES':
                return (
                    <>
                        <li><Link to="/customer">Customer</Link></li>
                        <li><Link to="/billing">Bill</Link></li>
                    </>
                );
            default:
                return <li>Access Denied</li>;
        }
    };

    return (
        <div className="home-container">
            <nav className="navbar">
                <ul className="nav-links">
                    {getNavLinks()}
                </ul>
            </nav>
            <div className="content">
                <h1>Welcome to the Dashboard</h1>
            </div>
        </div>
    );
}

export default Home;
