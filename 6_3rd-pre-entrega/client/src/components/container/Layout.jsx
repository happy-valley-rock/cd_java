import { Box, Grid } from '@mui/material';
import { Footer, ResponsiveDrawer } from '~/components/container';
import { DrawerHeader } from '~/components/container/ResponsiveDrawer/styles';
import { useTheme } from '@mui/material/styles';
import { Outlet } from "react-router-dom";
import { ghost } from '~/assets/colors';


export default function Layout() {
  const theme = useTheme();

  return (
    <ResponsiveDrawer>
      <Box sx={{ bg: 'theme.palette.custom.light', flexGrow: 1 }} component="main">
        <DrawerHeader />
        <Box sx={{ flexGrow: 1, p: 3, backgroundColor: ghost }}>
          <Grid container sx={{
            minHeight: '90vh',
            alignItems: 'flex-start',
            backgroundColor: 'transparent',
            }}
          >
            <Grid item lg={0.5}/>
            <Grid
              sx={{
                display: 'flex',
                width: '100%',
                gap: theme.spacing(3),
                flexDirection: 'column'
              }}
              lg={11}
              item
            >
              <Box component='main'>
                <Outlet />
              </Box>
            </Grid>
            <Grid item lg={0.5}/>
          </Grid>
        </Box>
        <Footer />
      </Box>
    </ResponsiveDrawer>
  );
}
