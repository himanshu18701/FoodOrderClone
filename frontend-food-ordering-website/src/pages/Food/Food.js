import React, { useEffect, useState } from 'react'
import {useNavigate, useParams} from 'react-router-dom';
import { getById } from '../../services/foodservices';
import classes from './food.module.css';
import Price from '../../components/Price/Price';
import { useCart } from '../../Hooks/useCart';
import NotFound from '../../components/NotFound/NotFound';
export default function Food() {

    const [food, setFood] = useState({});
    const {id}=useParams();
    const {addToCart}=useCart();
    const navigate=useNavigate();

    const handleAddToCart=()=>{
        addToCart(food);
        navigate('/cart');
    }
    useEffect(() => {
        getById(id).then(setFood);
      }, [id]);

      return (
        <>
          {!food ? (
            <NotFound message="Food Not Found!" linkText="Back To Homepage"/>
          ) : (
            <div className={classes.container}>
              <img className={classes.image}
                   src={food.imageUrl}
                   alt={food.name}
              />
              <div className={classes.details}>
                <div className={classes.header}>
                  <span className={classes.name}>{food.name}</span>
                </div>
                <div className={classes.price}>
                  <Price price={food.price}/>
                </div>
                <button onClick={handleAddToCart}>Add To Cart</button>
              </div>
            </div>
          )}
        </>
      );
} 
