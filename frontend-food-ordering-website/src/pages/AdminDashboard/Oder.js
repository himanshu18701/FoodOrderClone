import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Orders = () => {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    axios.get('/api/admin/orders')
      .then(response => setOrders(response.data))
      .catch(error => console.error('Error fetching orders', error));
  }, []);

  return (
    <div>
      <h2>Orders</h2>
    </div>
  );
};

export default Orders;
