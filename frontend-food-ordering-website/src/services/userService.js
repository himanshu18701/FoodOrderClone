import axios from '../../src/axiosConfig';

export const getUser = () => {
  const name = localStorage.getItem('name');
  return name ? { name } : null;
};

export const login = async (email, password) => {
  const response = await axios.post('/api/users/login', { email, password });
  const { token, name, id,address } = response.data;
  localStorage.setItem('token', token);
  localStorage.setItem('name', name);
  localStorage.setItem('userId', id);
  localStorage.setItem('address',address);
  return response.data;
};

export const register = async registerData => {
  const response = await axios.post('/api/users/register', registerData);
  const { token, name } = response.data;
  localStorage.setItem('token', token);
  localStorage.setItem('name', name);
  return response.data;
};

export const logout = () => {
  localStorage.removeItem('token');
  localStorage.removeItem('name');
  localStorage.removeItem('userId');
  localStorage.removeItem('address');
};

export const updateProfile = async user => {
  const { data } = await axios.put('/api/users/updateProfile', user);
  localStorage.setItem('user', JSON.stringify(data));
  return data;
};

export const changePassword = async ({ userId, currentPassword, newPassword }) => {
  const requestBody = {
    userId,
    currentPassword,
    newPassword
  };
  await axios.put('/api/users/changePassword', requestBody);
};

export const getAll = async searchTerm => {
  const { data } = await axios.get('/api/users/getAll/' + (searchTerm ?? ''));
  return data;
};

export const toggleBlock = async userId => {
  const { data } = await axios.put('/api/users/toggleBlock/' + userId);
  return data;
};

export const getById = async userId => {
  const { data } = await axios.get('/api/users/getById/' + userId);
  return data;
};

export const updateUser = async userData => {
  const { data } = await axios.put('/api/users/update', userData);
  return data;
};
