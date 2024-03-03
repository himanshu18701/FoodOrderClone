import { useState, createContext, useContext, useEffect } from 'react';
import * as userService from '../services/userService';
import { toast } from 'react-toastify';

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem('token');
    const name = localStorage.getItem('name');
    const address=localStorage.getItem('address');
    if (token) {
      setUser({ token, name ,address});
    }
  }, []);

  const isLoggedIn = () => {
    return !!user;
  };

  const login = async (email, password) => {
    try {
      const response = await userService.login(email, password);
      const { token, name } = response;
      localStorage.setItem('token', token);
      localStorage.setItem('name', name);
      setUser({ token, name });
      toast.success('Login Successful');
    } catch (err) {
      toast.error(err.response.data);
    }
  };

  const register = async data => {
    try {
      const response = await userService.register(data);
      const { token, name } = response;
      localStorage.setItem('token', token);
      localStorage.setItem('name', name);
      setUser({ token, name });
      toast.success('Register Successful');
    } catch (err) {
      toast.error(err.response.data);
    }
  };

  const logout = () => {
    userService.logout();
    localStorage.removeItem('token');
    localStorage.removeItem('name');
    setUser(null);
    toast.success('Logout Successful');
  };

  const updateProfile = async updatedData => {
    const updatedUser = await userService.updateProfile(updatedData);
    setUser(updatedUser);
    toast.success('Profile Update Was Successful');
  };

  const changePassword = async ({ userId, currentPassword, newPassword }) => {
    try {
      await userService.changePassword({
        userId,
        currentPassword,
        newPassword
      });
      logout();
      toast.success('Password Changed Successfully, Please Login Again!');
    } catch (error) {
      toast.error('Password change failed');
      throw error;
    }
  };

  return (
    <AuthContext.Provider value={{ user, isLoggedIn, login, logout, register, updateProfile, changePassword }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
