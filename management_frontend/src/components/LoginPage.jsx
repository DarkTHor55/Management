import { useState } from 'react';
import * as jwt_decode from 'jwt-decode';
import { useNavigate } from 'react-router-dom';

function LoginPage() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const eSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch('http://localhost:8888/api/v1/users/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, password }),
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();

            const token = data.token;

            const decodedToken = jwt_decode.jwtDecode(token); 
            const userRole = decodedToken.role;
            const userEmail = decodedToken.email;

            
            localStorage.setItem('authToken', token);
            localStorage.setItem('userRole', userRole);
            localStorage.setItem('userEmail', userEmail);

            console.log('Login successful');
            console.log('Role:', userRole);
            console.log('Email:', userEmail);
            console.log(token);
            

                navigate('/home');
        } catch (error) {
            console.error('Error during login:', error);
        }
    };

    return (
        <form onSubmit={eSubmit}>
            <input 
                type="email" 
                value={email} 
                onChange={(e) => setEmail(e.target.value)} 
                placeholder="Email" 
            />
            <input 
                type="password" 
                value={password} 
                onChange={(e) => setPassword(e.target.value)} 
                placeholder="Password" 
            />
            <button type="submit">Login</button>
        </form>
    );
}

export default LoginPage;
