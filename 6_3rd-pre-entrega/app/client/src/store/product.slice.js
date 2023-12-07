import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import {
  STATUS_FULLFILLED,
  STATUS_PENDING,
  STATUS_REJECTED
} from "~/consts/index.js";
import { formatErrorResponse } from '~/helpers';
import { services } from '~/utils';

export const fetchGetProducts = createAsyncThunk(
  'product/getProducts',
  async (payload, thunkAPI) => {
    try {
      const response = await services.getProductList(payload);
      return response.data;
    } catch (e) {
      return thunkAPI.rejectWithValue(formatErrorResponse(error));
    }
  }
)

const initialState = {
  data: [],
  dataHashMap: {},
  status: 'idle'
}

const productSlice = createSlice({
  name: 'product',
  initialState,
  reducers: {
    cleanUp: (state) => {
      return initialState
    },
    addStock: (state, action) => {
      const productId = action.payload;
      if(state.dataHashMap[productId].stock < state.dataHashMap[productId].stockTop) {
        state.dataHashMap[productId].stock += 1;
        state.data[state.dataHashMap[productId].index].stock += 1;
      }
      return state;
    },
    removeStock: (state, action) => {
      const productId = action.payload;
      if(state.dataHashMap[productId].stock > 0) {
        state.dataHashMap[productId].stock -= 1;
        state.data[state.dataHashMap[productId].index].stock -= 1;
      }
      return state;
    }
  },
  extraReducers: (builder) => {
    builder
    .addCase(fetchGetProducts.pending, (state) => {
      state.status = STATUS_PENDING;
      return state;
    })
    .addCase(fetchGetProducts.fulfilled, (state, action) => {
      const productHashMap = {};
      const productListFormatted = action.payload.map((product, index) => {
        const taxes = (product.purchasePrice-product.sellPrice)/product.sellPrice*100;
        product['index'] = index;
        product['taxes'] = `${Math.trunc(taxes)}%`;
        product['price'] = `$${product.purchasePrice}`;
        product['stockTop'] = product.stock;
        productHashMap[`${product.id}`] = product;
        return product;
      });
      state.dataHashMap = productHashMap;
      state.data = productListFormatted;
      state.status = STATUS_FULLFILLED;
    })
    .addCase(fetchGetProducts.rejected, (state) => {
      state.status = STATUS_REJECTED;
      return state;
    })
  }
})

export const { cleanUp, addStock, removeStock } = productSlice.actions;

export default productSlice.reducer;
