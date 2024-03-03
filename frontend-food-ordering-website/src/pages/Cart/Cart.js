import React from 'react';
import { Link } from 'react-router-dom';
import classes from './cart.module.css';
import { useCart } from '../../Hooks/useCart';
import Title from '../../components/Title/Title';
import Price from '../../components/Price/Price';
import NotFound from '../../components/NotFound/NotFound';

const CartItem = ({ item, changeQuantity, removeFromCart }) => (
  <li key={item.food.id}>
    <div>
      <img src={item.food.imageUrl} alt={item.food.name} />
    </div>
    <div>
      <Link to={`/food/${item.food.id}`}>{item.food.name}</Link>
    </div>
    <div>
      <select
        value={item.quantity} 
        onChange={e => changeQuantity(item, Number(e.target.value))}
      >
        {[...Array(10).keys()].map(n => (
          <option key={n + 1} value={n + 1}>{n + 1}</option>
        ))}
      </select>
    </div>
    <div>
      <Price price={item.price} />
    </div>
    <div>
      <button
        className={classes.remove_button}
        onClick={() => removeFromCart(item.food.id)}
      >
        Remove
      </button>
    </div>
  </li>
);

export default function Cart() {
  const { cart, removeFromCart, changeQuantity } = useCart();

  return (
    <>
      <Title title="Cart Page" margin="1.5rem 0 0 2.5rem" />
      {cart.items.length === 0 ? (
        <NotFound message="Cart Page is Empty!" />
      ) : (
        <div className={classes.container}>
          <ul className={classes.list}>
            {cart.items.map(item => (
              <CartItem 
                key={item.food.id} 
                item={item} 
                changeQuantity={changeQuantity} 
                removeFromCart={removeFromCart} 
              />
            ))}
          </ul>
          <div className={classes.checkout}>
            <div>
              <div className={classes.foods_count}>{cart.totalCount}</div>
              <div className={classes.total_price}>
                <Price price={cart.totalPrice} />
              </div>
            </div>
            <Link to="/checkout">Proceed To Checkout</Link>
          </div>
        </div>
      )}
    </>
  );
}
