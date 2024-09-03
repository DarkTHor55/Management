import React, { useState, useEffect } from 'react';

const ReadBill = () => {
    const [bills, setBills] = useState([]);
    const [viewMode, setViewMode] = useState('view');

    useEffect(() => {
        const fetchBills = async () => {
            try {
                const response = await fetch('http://localhost:8082/api/v1/users/bill');
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                const data = await response.json();
                setBills(data);
            } catch (error) {
                console.error('Error fetching bills:', error);
            }
        };

        if (viewMode === 'view') {
            fetchBills();
        }
    }, [viewMode]);

    return (
        <div>
            <h2>Bill Management</h2>
            <div>
                <h3>Bill List</h3>
                <ul>
                    {bills.map((bill, index) => (
                        <li key={index}>
                            Customer ID: {bill.customerId}, Total Amount: {bill.totalAmount}, 
                            Payment Method: {bill.paymentMethod}, Payment Date: {bill.paymentDate}
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
};

export default ReadBill;
