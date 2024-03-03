import Header from'./components/Header/Header.js'
import './App.css';
import AppRoutes from './AppRoutes.js';
import Loading from './components/Loading/Loading.js'
import { useLoading } from './Hooks/useLoading.js';
import { setLoadingInterceptor} from './interceptors/loadingintercept.js';
import { useEffect } from 'react';

function App() {
  const {showLoading,hideLoading}=useLoading();
  useEffect(()=>{
    setLoadingInterceptor({showLoading,hideLoading});
  },[showLoading,hideLoading])
  return (
    <>
    <Loading/>
    <Header/>
    <AppRoutes/>
    </>
  );
}

export default App;
