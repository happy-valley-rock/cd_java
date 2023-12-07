import { configureStore } from '@reduxjs/toolkit';
import alertSlice from './alert.slice';
import clientSlice from './client.slice';
import productSlice from './product.slice';
import invoiceSlice from './invoice.slice';


export default configureStore({
  reducer: {
    product: productSlice,
    client: clientSlice,
    invoice: invoiceSlice,
    alert: alertSlice
  }
});
