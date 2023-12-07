import React from 'react';
import { Alert } from '~/components/common';
import { useDispatch, useSelector } from 'react-redux';
import { closeAlert } from '~/store/alert.slice';


export default function GlobalAlert() {
  const dispatch = useDispatch();
  const { severity, message, open, delay } = useSelector((state) => state.alert);

  function handleClose() {
    dispatch(closeAlert());
  }

  return (
    <>
      <Alert
        severity={severity}
        message={message}
        open={open}
        delay={delay}
        handleClose={handleClose}
      />
    </>
  );
}
