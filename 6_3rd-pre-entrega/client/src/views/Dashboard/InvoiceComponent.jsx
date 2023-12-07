import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { Grid, Paper, Typography, Box, Skeleton, Divider, Button } from "@mui/material";
import { TableCustom } from "~/components/common";
import { STATUS_PENDING } from '~/consts';
import { services } from '~/utils';


export default function InvoiceComponent({ data, dataTickets, handleOnClick, loading }) {
  const [t] = useTranslation();
	const [sizeMdLg, setSizeMdLg] = useState(8);

	const columnsDetail = [
    { label: t('invoice.description'), key: 'description' },
    { label: t('invoice.amount'), key: 'amount' },
  ];

	const columnsTicket = [
    { label: t('invoice.ticket'), key: 'id' }
  ];

	function handleOnClickRow(data) {
		services.getInvoiceTicket(data.id)
		.then(response => {
			const link = document.createElement('a');
      const fileName = `${data.id}-${data.createdAt}.pdf`;
			const blob = new Blob([response.data], { type: 'application/pdf' });
      link.href = window.URL.createObjectURL(blob);
      link.download = fileName;
      link.target = '_blank';
		
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
		})
		.catch(error => {
			console.error('Error downloading PDF: ', error);
		});
	}

	useEffect(() => {
		if(dataTickets.length > 0) setSizeMdLg(6);
		else setSizeMdLg(8);
	}, [dataTickets]) 

  return (
		<>
			{ (loading === STATUS_PENDING) ? (
					<Grid sx={{ mb: 2 }} item xs={12} sm={12} md={12} lg={12}><Skeleton height={350} variant="rounded" /></Grid>
      )
			:(
				<Paper sx={{ p: 4, mt: 4, height: '100%'}}>
					<Grid container spacing={2}>

						<Grid item xs={12} sm={12} md={4} lg={4}>
							<Typography variant="h5" >{t('invoice.title', {client: data.clientName})}</Typography>
							<Divider sx={{ my: 1 }}></Divider>
							<Typography variant="subtitle1" >{t('invoice.client', {client: data.clientName})}</Typography>
							<Typography variant="subtitle1" >{t('invoice.totalPrice', {total: data.totalPrice})}</Typography>
							<Typography variant="subtitle1" >{t('invoice.totalAmount', {total: data.totalAmount})}</Typography>
							<Button onClick={handleOnClick} variant="contained" sx={{ marginTop: 2 }}>{t('invoice.button')}</Button>
						</Grid>

						<Grid item xs={12} sm={12} md={sizeMdLg} lg={sizeMdLg}>
							<TableCustom
									columns={columnsDetail}
									content={data.products}
									showPaginator={false}
							/>
						</Grid>

						{ dataTickets.length > 0 &&
							<Grid item xs={12} sm={12} md={2} lg={2}>
							<TableCustom
									columns={columnsTicket}
									content={dataTickets}
									showPaginator={false}
									handleOnClickRow={handleOnClickRow}
							/>
						</Grid>
						}
		
					</Grid>
				</Paper>
			)}
		</>
  );
};
