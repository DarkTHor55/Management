
import { Link } from 'react-router-dom';

function AccessDenied() {
    return (
        <>
        <nav className="navbar">
                <ul className="nav-links">
                    <li><Link to="/profile">Profile</Link></li>
                    <li><Link to="/customer">Customer</Link></li>
                    <li><Link to="/payroll">Payroll</Link></li>
                    <li><Link to="/billing">Bill</Link></li>
                </ul>
            </nav>
        <div className="access-denied-container">
            <h1>Access Denied</h1>
            <p>You do not have permission to access this page.</p>
        </div>
        </>
    );
}

export default AccessDenied;
