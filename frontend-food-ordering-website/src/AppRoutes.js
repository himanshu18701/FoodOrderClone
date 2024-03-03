import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import HomePage from './pages/Home/HomePage';
import Food from './pages/Food/Food';
import Cart from './pages/Cart/Cart';
import LoginPage from './pages/Login/LoginPage';
import SignUpPage from './components/SignUp/SignUpPage';
import ProfilePage from './pages/Profile/ProfilePage';
import AuthRoute from './components/AuthRoute/AuthRoute';
import { useAuth } from './Hooks/useAuth';
import OrdersPage from './pages/Orders/OrderPage';
import CheckoutPage from './pages/Checkout/CheckOutPage';

export default function AppRoutes() {
  const { user } = useAuth();

  return (
    <Routes>
      <Route path="/login" element={<LoginPage />} />
      <Route path="/register" element={<SignUpPage />} />
      <Route path="/" element={
        <AuthRoute>
          <HomePage />
        </AuthRoute>
      } />
      <Route path="/search/:searchTerm" element={
        <AuthRoute>
          <HomePage />
        </AuthRoute>
      } />
      <Route path="/tag/:tag" element={
        <AuthRoute>
          <HomePage />
        </AuthRoute>
      } />
      <Route path="/food/:id" element={
        <AuthRoute>
          <Food />
        </AuthRoute>
      } />
      <Route path="/cart" element={
        <AuthRoute>
          <Cart />
        </AuthRoute>
      } />
      <Route path="/profile" element={
        <AuthRoute>
          <ProfilePage />
        </AuthRoute>
      } />
      <Route path="/orders" element={
        <AuthRoute>
          <OrdersPage />
        </AuthRoute>
      } />
      <Route path="/checkout" element={
        <AuthRoute>
          <CheckoutPage />
        </AuthRoute>
      } />
      <Route path="*" element={<Navigate to={user ? "/" : "/login"} />} />
    </Routes>
  );
}
