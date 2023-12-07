import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import {
  STATUS_FULLFILLED,
  STATUS_PENDING,
  STATUS_REJECTED
} from "~/consts/index.js";
import { formatErrorResponse } from '~/helpers';
import { services } from '~/utils';

export const fetchGetClients = createAsyncThunk(
  'client/getClients',
  async (payload, thunkAPI) => {
    try {
      const response = await services.getClientList(payload);
      return response.data;
    } catch (error) {
      return thunkAPI.rejectWithValue(formatErrorResponse(error));
    }
  }
)

const initialState = {
  data: null,
  status: 'idle'
}

const clientSlice = createSlice({
  name: 'client',
  initialState,
  reducers: {
    cleanUp: () => {
      return initialState
    }
  },
  extraReducers: (builder) => {
    builder
    .addCase(fetchGetClients.pending, (state) => {
      state.status = STATUS_PENDING;
      return state;
    })
    .addCase(fetchGetClients.fulfilled, (state, action) => {
      const clientListFormatted = action.payload.map(client => {
        client['name'] = `${client.firstName} ${client.lastName}`;
        return client;
      });
      state.data = clientListFormatted;
      state.status = STATUS_FULLFILLED;
      return state;
    })
    .addCase(fetchGetClients.rejected, (state) => {
      state.status = STATUS_REJECTED;
      return state;
    })
  }
})

export const {cleanUp} = clientSlice.actions;

export default clientSlice.reducer;
