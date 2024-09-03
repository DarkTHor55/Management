import React, { useState, useEffect } from 'react';

const Bill = () => {
    const [bills, setBills] = useState([]);
    const [customerId, setCustomerId] = useState('');
    const [totalAmount, setTotalAmount] = useState('');
    const [paymentMethod, setPaymentMethod] = useState('');
    const [paymentDate, setPaymentDate] = useState('');
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

    const handleAddBill = async () => {
        const newBill = {
            customerId: parseInt(customerId),
            totalAmount: parseFloat(totalAmount),
            paymentMethod,
            paymentDate,
        };

        try {
            const response = await fetch('http://localhost:8082/api/v1/users/bill', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(newBill),
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            setBills([...bills, newBill]);
            setCustomerId('');
            setTotalAmount('');
            setPaymentMethod('');
            setPaymentDate('');
            alert('Bill added successfully');
        } catch (error) {
            console.error('Error adding bill:', error);
            alert('Error adding bill');
        }
    };

    const handleSwitchMode = (mode) => {
        setViewMode(mode);
    };

    return (
        <div>
            <h2>Bill Management</h2>
            <button onClick={() => handleSwitchMode('create')}>Create Bill</button>
            <button onClick={() => handleSwitchMode('view')}>View Bills</button>

            {viewMode === 'create' && (
                <div>
                    <h3>Create Bill</h3>
                    <form onSubmit={e => { e.preventDefault(); handleAddBill(); }}>
                        <input 
                            type="number" 
                            placeholder="Customer ID" 
                            value={customerId} 
                            onChange={(e) => setCustomerId(e.target.value)} 
                            required 
                        />
                        <input 
                            type="number" 
                            placeholder="Total Amount" 
                            value={totalAmount} 
                            onChange={(e) => setTotalAmount(e.target.value)} 
                            required 
                        />
                        <input 
                            type="text" 
                            placeholder="Payment Method" 
                            value={paymentMethod} 
                            onChange={(e) => setPaymentMethod(e.target.value)} 
                            required 
                        />
                        <input 
                            type="date" 
                            placeholder="Payment Date" 
                            value={paymentDate} 
                            onChange={(e) => setPaymentDate(e.target.value)} 
                            required 
                        />
                        <button type="submit">Add Bill</button>
                    </form>
                </div>
            )}

            {viewMode === 'view' && (
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
            )}
        </div>
    );
};

export default Bill;
