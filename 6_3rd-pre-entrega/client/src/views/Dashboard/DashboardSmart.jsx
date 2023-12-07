import React, { useEffect, useState } from 'react';
import DashboardDumb from './DashboardDumb';
import { useSelector, useDispatch } from 'react-redux';
import { fetchGetClients } from '~/store/client.slice';
import { fetchGetProducts, addStock, removeStock } from '~/store/product.slice';
import { fetchPostInvoice } from '~/store/invoice.slice';


export default function DashboardSmart() {
  const dispatch = useDispatch();
  const initialState = { products: [], totalPrice: 0, totalAmount: 0, clientName: 'none' };
  const [cart, setCart] = useState(initialState);
  const clientStore = useSelector((store) => store.client);
  const productStore = useSelector((store) => store.product);
  const invoiceStore = useSelector((store) => store.invoice);


  function fetchClientsAndProducts() {
    dispatch(fetchGetClients());
    dispatch(fetchGetProducts());
  }

  useEffect(() => {
    fetchClientsAndProducts();
  }, []);

  function handleAddStock(productId) {
    dispatch(addStock(productId));
  }

  function handleRemoveStock(productId) {
    dispatch(removeStock(productId));
  }

  function handleCreateInvoice(data) {
    let { clientName, totalAmount, totalPrice, ...requestData } = data;
    requestData.products = requestData.products.map(p => ({ productId: p.productId, amount: p.amount }));
    dispatch(fetchPostInvoice(requestData))
    .then(res => {
      fetchClientsAndProducts();
      setCart(initialState);
    })
    .catch(err => console.error(err));
  }

  return (
    <>
      <DashboardDumb
        clientData={clientStore}
        productData={productStore}
        handleRemoveStock={handleRemoveStock}
        handleAddStock={handleAddStock}
        handleCreateInvoice={handleCreateInvoice}
        loadingRequest={invoiceStore.status}
        dataTickets={invoiceStore.invoices}
        cart={cart}
        setCart={setCart}
      />
    </>
  );
};
