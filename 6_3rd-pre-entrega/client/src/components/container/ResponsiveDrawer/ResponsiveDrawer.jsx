import React, { useEffect, useState } from 'react';
import {
  CssBaseline,
  Typography,
  Box,
  Toolbar,
} from '@mui/material';
import { withRouter } from '~/hoc';
import useStyles, { AppBar } from './styles';
import { useTheme } from '@mui/material/styles';


function ResponsiveDrawer(props) {
  const theme = useTheme();
  const styles = useStyles(theme);
  const { children } = props;

  return (
    <Box sx={{ display: 'flex' }}>
      <CssBaseline />
      <AppBar position="fixed" open={true}>
        <Toolbar sx={styles.toolbar}>
          <Box sx={{ display: 'flex', alignItems: 'center' }}>
            <Box sx={{ display: 'flex', alignItems: 'end', gap: 1 }}>
              <Typography variant="h5" color="primary" noWrap sx={{ lineHeight: 1, fontWeight: 600 }}>
              POS
              </Typography>
              <Typography variant="h5" noWrap sx={{ lineHeight: 1, overflow: 'inherit' }}>
              Simple
              </Typography>
            </Box>
          </Box>
        </Toolbar>
      </AppBar>
      {children}
    </Box>
  );
}

export default withRouter(ResponsiveDrawer);
