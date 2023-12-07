import React, { useRef } from 'react';
import { Link } from 'react-router-dom';
import { Box, Button, Typography } from '@mui/material';
import { useTheme } from '@mui/material/styles';
import GppBadIcon from '@mui/icons-material/GppBad';
import { useTranslation } from 'react-i18next';
import { ROUTES } from '~/consts';


export default function ErrorScreen({
  titleTab="errors.general.titleTab",
  title='errors.general.title',
  subtitle='errors.general.subtitle',
  button="errors.general.button",
  Icon=GppBadIcon
}) {
  const theme = useTheme();
  const [t] = useTranslation();
  const redirectToHome = useRef();
  document.title = t('errors.notfound.titleTab');

  return (
    <Box
      sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        gap: 4,
        width: '100%',
        height: '80vh'
      }}
    >
      <Typography variant="h1" sx={{ color: theme.palette.primary.main}}>
        Oups!
      </Typography>
      <Box
        sx={{
          display: 'flex',
          backgroundColor: theme.palette.primary[100],
          borderRadius: '100%',
          width: '18rem',
          height: '18rem',
          justifyContent: 'center',
          alignItems: 'center'
        }}
      >
        <Box
          sx={{
            display: 'flex',
            backgroundColor: theme.palette.primary[200],
            borderRadius: '100%',
            width: '15rem',
            height: '15rem',
            justifyContent: 'center',
            alignItems: 'center'
          }}
        >
          <Box
            sx={{
              display: 'flex',
              backgroundColor: theme.palette.primary[400],
              borderRadius: '100%',
              width: '12rem',
              height: '12rem',
              justifyContent: 'center',
              alignItems: 'center'
            }}
          >
            <Icon
              sx={{
                fontSize: '11rem',
                color: theme.palette.grey[200]
              }}
            />
          </Box>
        </Box>
      </Box>
      <Box
        sx={{
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          gap: 2,
          width: '100%',
        }}
      >
        <Typography variant="h4">
          {t(title)}
        </Typography>
        <Typography variant="subtitle1" sx={{ color: theme.palette.grey[600]}}>
          {t(subtitle)}
        </Typography>
      </Box>
      <Button onClick={() => redirectToHome.current.click()} variant="contained" size="large">
        {t(button)}
      </Button>
      <Link to={ROUTES.HOME} ref={redirectToHome} style={{ 'display': 'none' }} />
    </Box>
  );
}
