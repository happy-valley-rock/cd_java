import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { jungle } from '~/assets/colors';
import { Grid, Paper, Typography, Box, Skeleton } from "@mui/material";
import { useForm } from "react-hook-form";
import { HeadlinePage, TableCustom } from "~/components/common";
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import RemoveCircleOutlineIcon from '@mui/icons-material/RemoveCircleOutline';
import InvoiceComponent from './InvoiceComponent';
import DataList from './DataList';



export default function DashboardDumb({
    clientData,
    productData,
    handleAddStock,
    handleRemoveStock,
    handleCreateInvoice,
    dataTickets,
    loadingRequest,
    cart,
    setCart
  }) {
  const [t] = useTranslation();
  document.title = t('dashboard.titleTab');

  function handleSelectClient(data) {
    setCart({ ...cart, clientId: data.id, clientName: data.name });
  }

  function validEmptyCart() {
    if(!(cart?.products)) setCart({...cart, products: []});
  }

  function checkIsProductAdded(product) {
    const productIndexFounded = cart.products.findIndex((p) => p.productId === product.id);
    return productIndexFounded;
  }

  function handleAddProduct(data) {
    validEmptyCart();
    const index = checkIsProductAdded(data);
    
    if(data.stock > 0) {
      cart.totalPrice += data.purchasePrice;
      cart.totalAmount += 1;
    }
    
    if(index === -1) {
      setCart({ ...cart, products: [...cart.products, { productId: data.id, amount: 1, description: data.description }] });
    } else {
      if(data.stock > 0) cart.products[index].amount += 1;
      setCart({ ...cart });
    }
    handleRemoveStock(data.id);
  }

  function handleRemoveProduct(data) {
    validEmptyCart();
    const index = checkIsProductAdded(data);
    
    if(data.stock < data.stockTop) {
      cart.products[index].amount -= 1; 
      cart.totalAmount -= 1;
      cart.totalPrice -= data.purchasePrice;
    }
    
    if(index !== -1) {
      if(cart.products[index].amount <= 0) {
        cart.products = cart.products.filter(p => p.productId !== data.id);
      }
      setCart({ ...cart });
    }
    handleAddStock(data.id);
  }

  function handleOnClick() {
    handleCreateInvoice(cart);
  }

  useEffect(() => {
    validEmptyCart();
  });

  const productColumns = [
    { label: t('product.description'), key: 'description' },
    { label: t('product.sellPrice'), key: 'price' },
    { label: t('product.taxes'), key: 'taxes' },
    { label: t('product.stock'), key: 'stock' },
  ];

  const clientColumns = [
    { label: t('client.name'), key: 'name' },
    { label: t('client.documentNumber'), key: 'documentNumber' },
    { label: t('client.documentType'), key: 'documentType' }
  ];

  const actionsProduct = [
    { handle: handleAddProduct, icon: <AddCircleOutlineIcon/> },
    { handle: handleRemoveProduct, icon: <RemoveCircleOutlineIcon/> }
  ];

  return (
    <>
      <InvoiceComponent data={cart} dataTickets={dataTickets} handleOnClick={handleOnClick} loading={loadingRequest}/>
      <Grid container spacing={2}>
        <DataList {...clientData} handleOnClick={handleSelectClient} columns={clientColumns} loading={loadingRequest}/>
        <DataList {...productData} actions={actionsProduct} columns={productColumns} loading={loadingRequest}/>
      </Grid>      
    </>
  );
};
