import { Navigate } from 'react-router-dom';
import { useAuth } from '../../Hooks/useAuth';

export default function AuthRoute({ children }) {
  const { user } = useAuth();

  return user ? children : <Navigate to="/login" replace />;
}