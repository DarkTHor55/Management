import './App.css'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoginPage from './components/LoginPage';
import SignupPage from './components/SignupPage';
import Customer from './components/Customer';
import PayrollPage from './components/PayrollPage';
import Home from './components/Home';
import UserManagement from './components/UserManagement';
import AccessDenied from './components/AccessDenied';
import Bill from './components/Bill';
import ReadPayroll from './components/ReadPayroll';
import ReadBill from './components/ReadBill';

function App() {
  return (
    <>
      <Router>
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/signup" element={<SignupPage />} />
        <Route path="//access-denied" element={<AccessDenied />} />
        <Route path="/home" element={<Home />} />
        <Route path="/profile" element={<UserManagement />} />
        <Route path="/customer" element={<Customer />} />
        <Route path="/payroll" element={<PayrollPage />} />
        <Route path="/read-payroll" element={<ReadPayroll />} />
        <Route path="/billing" element={<Bill />} />
        <Route path="/read-billing" element={<ReadBill />} />
      </Routes>
    </Router>
    </>
  )
}

export default App
