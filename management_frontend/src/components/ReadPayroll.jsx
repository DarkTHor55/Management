import React, { useState, useEffect } from 'react';

const ReadPayroll = () => {
    const [payrolls, setPayrolls] = useState([]);

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

        fetchPayrolls();
    }, []);

    return (
        <div>
            <h2>Payroll Management</h2>

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
        </div>
    );
};

export default ReadPayroll;
