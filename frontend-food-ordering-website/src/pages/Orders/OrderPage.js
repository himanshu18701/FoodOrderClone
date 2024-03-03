import React, { useEffect, useReducer } from 'react';
import { Link, useParams } from 'react-router-dom';
import { getAll, getAllStatus } from '../../services/orderService';
import { getById as getFoodById } from '../../services/foodservices';
import classes from './orderpage.module.css';
import Title from '../../components/Title/Title';
import DateTime from '../../components/DateTime/DateTime';
import Price from '../../components/Price/Price';

const initialState = { allStatus: [], orders: [], foodDetails: {} };
const reducer = (state, action) => {
  const { type, payload } = action;
  switch (type) {
    case 'ALL_STATUS_FETCHED':
      return { ...state, allStatus: payload };
    case 'ORDERS_FETCHED':
      return { ...state, orders: payload };
    case 'FOOD_DETAILS_FETCHED':
      return { ...state, foodDetails: { ...state.foodDetails, [payload._id]: payload } };
    default:
      return state;
  }
};

export default function OrdersPage() {
  const [{ allStatus, orders, foodDetails }, dispatch] = useReducer(reducer, initialState);

  const { filter } = useParams();

  useEffect(() => {
    getAllStatus().then(status => {
      dispatch({ type: 'ALL_STATUS_FETCHED', payload: status });
    });

    getAll(filter).then(orders => {
      dispatch({ type: 'ORDERS_FETCHED', payload: orders });

      orders.forEach(order => {
        order.items.forEach(item => {
          if (!foodDetails[item.foodId]) {
            getFoodById(item.foodId).then(foodDetail => {
              dispatch({ type: 'FOOD_DETAILS_FETCHED', payload: foodDetail });
            });
          }
        });
      });
    });
  }, [filter, foodDetails]);

  return (
    <div className={classes.container}>
      <Title title="Orders" margin="1.5rem 0 0 .2rem" fontSize="1.9rem" />

      {orders.map(order => (
        <div key={order._id} className={classes.order_summary}>
          <div className={classes.header}>
            <span>{order._id}</span>
            <span><DateTime date={order.createdAt} /></span>
            <span>{order.status}</span>
          </div>
          <div className={classes.items}>
            {order.items.map(item => {
              const foodDetail = foodDetails[item.foodId];
              return (
                <Link key={item.foodId} to={`/food/${item.foodId}`}>
                  <img src={foodDetail?.imageUrl} alt={foodDetail?.name} />
                  <div>{foodDetail?.name}</div>
                </Link>
              );
            })}
          </div>
          <div className={classes.footer}>
            <div>
              <Link to={`/track/${order._id}`}>Show Order</Link>
            </div>
            <div>
              <span className={classes.price}>
                <Price price={order.totalPrice} />
              </span>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}
