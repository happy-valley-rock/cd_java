import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import {
  STATUS_FULLFILLED,
  STATUS_PENDING,
  STATUS_REJECTED
} from "~/consts/index.js";
import { formatErrorResponse } from '~/helpers';
import { openAlert } from './alert.slice';
import { services } from '~/utils';


export const fetchPostInvoice = createAsyncThunk(
  'invoice/postInvoices',
  async (payload, thunkAPI) => {
    try {
      const response = await services.postInvoice(payload);
      thunkAPI.dispatch(openAlert({ severity: 'success', message: 'invoice.alert' }));
      return response.data;
    } catch (error) {
      thunkAPI.dispatch(openAlert({ severity: 'error', message: error.response.data.error }));
      return thunkAPI.rejectWithValue(formatErrorResponse(error));
    }
  }
)

const initialState = {
  invoices: [],
  status: 'idle'
}

const invoiceSlice = createSlice({
  name: 'invoice',
  initialState,
  reducers: {
    cleanUp: () => {
      return initialState
    },
  },
  extraReducers: (builder) => {
    builder
    .addCase(fetchPostInvoice.pending, (state) => {
      state.status = STATUS_PENDING;
      return state;
    })
    .addCase(fetchPostInvoice.fulfilled, (state, action) => {
      state.status = STATUS_FULLFILLED;
      state.invoices.push(action.payload);
      return state;
    })
    .addCase(fetchPostInvoice.rejected, (state) => {
      state.status = STATUS_REJECTED;
      return state;
    })
  }
})

export const {cleanUp} = invoiceSlice.actions;

export default invoiceSlice.reducer;
