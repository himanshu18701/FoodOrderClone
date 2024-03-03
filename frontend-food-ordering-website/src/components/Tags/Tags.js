import React from 'react';
import { Link } from 'react-router-dom';
import classes from './tags.module.css';

export default function Tags({ tags, forFoodPage }) {
  const allTags=['All',...tags];
  return (
    <div
      className={classes.container}
      style={{
        justifyContent: forFoodPage ? 'start' : 'center',
      }}
    >
      {allTags.map(tag => (
        <Link key={tag} to={`/tag/${tag}`}>
          {tag}
          {!forFoodPage}
        </Link>
      ))}
    </div>
  );
}