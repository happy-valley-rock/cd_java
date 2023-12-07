import React from 'react';
import { useTranslation } from 'react-i18next';
import { Grid, Paper, Typography, Box, Skeleton } from "@mui/material";
import { TableCustom } from "~/components/common";
import { STATUS_FULLFILLED, STATUS_PENDING } from '~/consts';



export default function DataList({ data, status, handleOnClick, columns, actions, loading }) {
  const [t] = useTranslation();

  return (
    <>
      { (status !== STATUS_FULLFILLED && status !== 'none' || loading === STATUS_PENDING) ? (
        <Grid item xs={12} sm={12} md={6} lg={6}><Skeleton height={350} variant="rounded" /></Grid>
      )
      :(
        <Grid item xs={12} sm={12} md={6} lg={6}>
          <Paper sx={{ p: 4, mt: 2, height: '100%' }}>
            <TableCustom
              columns={columns}
              content={data}
              showPaginator={false}
              handleOnClickRow={handleOnClick}
              actions={actions}
            />
          </Paper>
        </Grid>
      )} 
    </>
  );
};
