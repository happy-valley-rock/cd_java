import { createSlice } from '@reduxjs/toolkit';


const initialState = {
  severity: 'info',
  open: false,
  message: '',
  delay: 4000
}

const alertSlice = createSlice({
  name: 'alert',
  initialState,
  reducers: {
    openAlert: (state, action) => {
      const { severity='info', message='not message alert', delay=4000 } = action.payload;
      return { ...state, severity, message, delay, open: true };
    },
    closeAlert: (state) => {
      return { ...state, open: false, message: '' };
    }
  }
});

export const { openAlert, closeAlert } = alertSlice.actions;

export default alertSlice.reducer;
