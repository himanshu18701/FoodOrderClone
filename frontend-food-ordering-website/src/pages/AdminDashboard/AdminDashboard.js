import React from 'react';
import Orders from './Orders';
import FoodItems from './FoodItems';

const AdminDashboard = () => {
  return (
    <div>
      <h1>Admin Dashboard</h1>
      <Orders />
      <FoodItems />
    </div>
  );
};

export default AdminDashboard;
