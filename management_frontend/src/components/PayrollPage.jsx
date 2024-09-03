import React, { useState, useEffect } from 'react';

const Payroll = () => {
    const [payrolls, setPayrolls] = useState([]);
    const [employeeId, setEmployeeId] = useState('');
    const [payDate, setPayDate] = useState('');
    const [basicSalary, setBasicSalary] = useState('');
    const [allowances, setAllowances] = useState('');
    const [deductions, setDeductions] = useState('');
    const [bonuses, setBonuses] = useState('');
    const [viewMode, setViewMode] = useState('view'); // 'view' or 'create'

    useEffect(() => {
        const fetchPayrolls = async () => {
            try {
                const response = await fetch('http://localhost:8084/api/v1/users/payroll'); // Update the URL as needed

                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                const data = await response.json();
                setPayrolls(data);
            } catch (error) {
                console.error('Error fetching payrolls:', error);
            }
        };

        if (viewMode === 'view') {
            fetchPayrolls();
        }
    }, [viewMode]);

    const handleAddPayroll = async () => {
        const newPayroll = {
            employeeId: parseInt(employeeId),
            payDate,
            basicSalary: parseFloat(basicSalary),
            allowances: parseFloat(allowances),
            deductions: parseFloat(deductions),
            bonuses: parseFloat(bonuses),
        };

        try {
            const response = await fetch('http://localhost:8084/api/v1/users/payroll/create', { 
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(newPayroll),
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            setPayrolls([...payrolls, newPayroll]);
            setEmployeeId('');
            setPayDate('');
            setBasicSalary('');
            setAllowances('');
            setDeductions('');
            setBonuses('');
            alert('Payroll added successfully');
        } catch (error) {
            console.error('Error adding payroll:', error);
            alert('Error adding payroll');
        }
    };

    const handleSwitchMode = (mode) => {
        setViewMode(mode);
    };

    return (
        <div>
            <h2>Payroll Management</h2>
            <button onClick={() => handleSwitchMode('create')}>Create Payroll</button>
            <button onClick={() => handleSwitchMode('view')}>View Payrolls</button>

            {viewMode === 'create' && (
                <div>
                    <h3>Create Payroll</h3>
                    <form onSubmit={e => { e.preventDefault(); handleAddPayroll(); }}>
                        <input 
                            type="number" 
                            placeholder="Employee ID" 
                            value={employeeId} 
                            onChange={(e) => setEmployeeId(e.target.value)} 
                            required 
                        />
                        <input 
                            type="date" 
                            placeholder="Pay Date" 
                            value={payDate} 
                            onChange={(e) => setPayDate(e.target.value)} 
                            required 
                        />
                        <input 
                            type="number" 
                            placeholder="Basic Salary" 
                            value={basicSalary} 
                            onChange={(e) => setBasicSalary(e.target.value)} 
                            required 
                        />
                        <input 
                            type="number" 
                            placeholder="Allowances" 
                            value={allowances} 
                            onChange={(e) => setAllowances(e.target.value)} 
                            required 
                        />
                        <input 
                            type="number" 
                            placeholder="Deductions" 
                            value={deductions} 
                            onChange={(e) => setDeductions(e.target.value)} 
                            required 
                        />
                        <input 
                            type="number" 
                            placeholder="Bonuses" 
                            value={bonuses} 
                            onChange={(e) => setBonuses(e.target.value)} 
                            required 
                        />
                        <button type="submit">Add Payroll</button>
                    </form>
                </div>
            )}

            {viewMode === 'view' && (
                <div>
                    <h3>Payroll List</h3>
                    <ul>
                        {payrolls.map((payroll, index) => (
                            <li key={index}>
                                Employee ID: {payroll.employeeId}, Pay Date: {payroll.payDate}, 
                                Basic Salary: {payroll.basicSalary}, Allowances: {payroll.allowances}, 
                                Deductions: {payroll.deductions}, Bonuses: {payroll.bonuses}
                            </li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    );
};

export default Payroll;
