import React from 'react';
import PropTypes from 'prop-types';
import classes from './button.module.css';

export default function Button({
  type,
  text,
  onClick,
  color,
  backgroundColor,
  fontSize,
  width,
  height,
  style,
}) {
  const buttonStyle = {
    color,
    backgroundColor,
    fontSize,
    width,
    height,
    ...style,
  };

  return (
    <div className={classes.container}>
      <button
        style={buttonStyle}
        type={type}
        onClick={onClick}
      >
        {text}
      </button>
    </div>
  );
}

Button.propTypes = {
  type: PropTypes.string,
  text: PropTypes.string,
  onClick: PropTypes.func,
  color: PropTypes.string,
  backgroundColor: PropTypes.string,
  fontSize: PropTypes.string,
  width: PropTypes.string,
  height: PropTypes.string,
  style: PropTypes.object,
};

Button.defaultProps = {
  type: 'button',
  text: 'Submit',
  backgroundColor: '#e72929',
  color: 'white',
  fontSize: '1.3rem',
  width: '12rem',
  height: '3.5rem',
  style: {},
};
