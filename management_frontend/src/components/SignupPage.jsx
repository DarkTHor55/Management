import React, { useState } from "react";

const SignupPage = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("");
  const [error, setError] = useState(""); // To store error messages if needed
  const [success, setSuccess] = useState(""); // To store success messages if needed

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    try {
      const response = await fetch("http://localhost:8888/api/v1/users/signin", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ name, email, password, role }),
      });
      
      if (!response.ok) {
        throw new Error("Network response was not ok.");
      }

      const result = await response.json();
      
      if (result.success) {
        setSuccess("Signup successful!");
      } else {
        setError(result.message || "Signup failed.");
      }
    } catch (error) {
      setError(error.message || "An error occurred.");
    }
  };

  return (
    <div className="container">
      <h2>Sign Up</h2>
      <form onSubmit={handleSubmit}>
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
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <select value={role} onChange={(e) => setRole(e.target.value)} required>
          <option value="">Select Role</option>
          <option value="ADMINISTRATOR">Administrator</option>
          <option value="HR">HR</option>
          <option value="SALES">Sales</option>
          <option value="ACCOUNTANT">Accountant</option>
        </select>
        <button type="submit">Sign Up</button>
        {error && <p className="error">{error}</p>}
        {success && <p className="success">{success}</p>}
      </form>
    </div>
  );
};

export default SignupPage;
