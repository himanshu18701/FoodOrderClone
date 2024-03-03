import React, { useEffect, useState } from 'react';
import { useCart } from '../../Hooks/useCart';
import { useAuth } from '../../Hooks/useAuth';
import { useNavigate } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import { toast } from 'react-toastify';
import { createOrder } from '../../services/orderService';
import classes from './checkout.module.css';
import Title from '../../components/Title/Title';
import Input from '../../components/Input/Input';
import Button from '../../components/Button/Button';
import OrderItem from '../../components/OrderItem/OrderItem';
import Map from '../../components/Map/Map';

export default function CheckoutPage() {
  const { cart } = useCart();
  const { user } = useAuth();
  const navigate = useNavigate();
  const [order, setOrder] = useState({ ...cart, items: cart.items.map(item => ({ ...item, food: item.food.id })) }); // 
  const [address, setAddress] = useState('');

  const { register, formState: { errors }, handleSubmit } = useForm();

  useEffect(() => {
    const storedAddress = localStorage.getItem('address');
    if (storedAddress) {
      setAddress(storedAddress);
    }
  }, []);

  const submit = async data => {
    if (!order.addressLatLng) {
      toast.warning('Please select your location on the map');
      return;
    }
  
    try {
      const orderToSubmit = { ...order, name: data.name, address:address };
      await createOrder(orderToSubmit);
      navigate('/payment');
      window.location.reload();
    } catch (error) {
      console.error('Error creating order:', error);
      toast.error('Failed to create order. Please try again.');
    }
  };
  

  const onLocationChange = (latLng) => {
    setOrder({ ...order, addressLatLng: latLng });
  };

  return (
    <>
      <form onSubmit={handleSubmit(submit)} className={classes.container}>
        <div className={classes.content}>
          <Title title="Order Form" fontSize="1.6rem" />
          <div className={classes.inputs}>
            <Input defaultValue={user.name} label="Name" {...register('name')} error={errors.name} />
            <Input defaultValue={address} label="Address" {...register('address')} error={errors.address} />
          </div>
          <OrderItem order={order} />
        </div>
        <div>
          <Title title="Choose Your Location" fontSize="1.6rem" />
          <Map location={order.addressLatLng} onLocationChange={onLocationChange} />
        </div>
        <div className={classes.buttons_container}>
          <div className={classes.buttons}>
            <Button type="submit" text="Go To Payment" width="100%" height="3rem" />
          </div>
        </div>
      </form>
    </>
  );
}
