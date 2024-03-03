import axios from 'axios';
import { createBrowserHistory } from 'history';

// Create a history object
const history = createBrowserHistory();

axios.defaults.baseURL = process.env.NODE_ENV !== 'production' ? 'http://localhost:8080' : '/';

// Request interceptor for API calls
axios.interceptors.request.use(config => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
}, error => {
    return Promise.reject(error);
});

axios.interceptors.response.use(
    response => response,
    error => {
        if (error.response && (error.response.status === 401 || error.response.status === 403)) {
            history.push('/login');
        }
        return Promise.reject(error);
    }
);


export default axios;
