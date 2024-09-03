import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function Customer() {
    const [employees, setEmployees] = useState([]);
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [accessAllowed, setAccessAllowed] = useState(true);
    const [viewMode, setViewMode] = useState('view'); 
    const navigate = useNavigate();

    useEffect(() => {
        const fetchEmployees = async () => {
            const token = localStorage.getItem('authToken');
            
            if (!token) {
                setAccessAllowed(false);
                navigate('/home'); // Redirect if no token
                return;
            }

            try {
                const response = await fetch('http://localhost:8083/api/v1/users/customer', {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                });

                if (response.status === 403) {
                    setAccessAllowed(false);
                    navigate('/home'); // Redirect on 403
                    return;
                }

                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                const data = await response.json();
                setEmployees(data);
            } catch (error) {
                console.error('Error fetching employees:', error);
                setAccessAllowed(false);
                navigate('/home'); // Redirect on error
            }
        };

        if (viewMode === 'view') {
            fetchEmployees();
        }
    }, [viewMode, navigate]);

    const handleAddEmployee = async () => {
        const newEmployee = { name, email, phone };
        const token = localStorage.getItem('authToken');
        
        if (!token) {
            setAccessAllowed(false);
            navigate('/home'); // Redirect if no token
            return;
        }

        try {
            const response = await fetch('http://localhost:8083/api/v1/users/customer/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify(newEmployee),
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            setEmployees([...employees, newEmployee]);
            setName('');
            setEmail('');
            setPhone('');
            alert('Customer added successfully');
        } catch (error) {
            console.error('Error adding employee:', error);
            alert('Error adding customer');
        }
    };

    const handleSwitchMode = (mode) => {
        setViewMode(mode);
    };

    if (!accessAllowed) {
        return <div>Access not allowed. Redirecting...</div>; 
    }

    return (
        <div>
            <h2>Customer Management</h2>
            <div>
                <button onClick={() => handleSwitchMode('create')}>Create Customer</button>
                <button onClick={() => handleSwitchMode('view')}>View Customers</button>
            </div>
            <div style={{ marginTop: '20px' }}>
                {viewMode === 'create' && (
                    <div>
                        <h3>Create Customer</h3>
                        <form onSubmit={e => { e.preventDefault(); handleAddEmployee(); }}>
                            <input 
                                type="text" 
                                placeholder="Name" 
                                value={name} 
                                onChange={(e) => setName(e.target.value)} 
                                required 
                            />
                            <input 
                                type="email" 
                                placeholder="Email" 
                                value={email} 
                                onChange={(e) => setEmail(e.target.value)} 
                                required 
                            />
                            <input 
                                type="text" 
                                placeholder="Phone" 
                                value={phone} 
                                onChange={(e) => setPhone(e.target.value)} 
                                required 
                            />
                            <button type="submit">Add Customer</button>
                        </form>
                    </div>
                )}

                {viewMode === 'view' && (
                    <div>
                        <h3>Customer List</h3>
                        <ul>
                            {employees.map((employee, index) => (
                                <li key={index}>
                                    Name: {employee.name}, Email: {employee.email}, Phone: {employee.phone}
                                </li>
                            ))}
                        </ul>
                    </div>
                )}
            </div>
        </div>
    );
}

export default Customer;
