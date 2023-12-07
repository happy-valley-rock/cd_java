import React from 'react';
import { useTranslation } from 'react-i18next';
import { Box, Typography, Button, IconButton } from '@mui/material';
import { useTheme } from '@mui/material/styles';
import { Link } from 'react-router-dom';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';


const useStyle = (theme) => ({
  headline: {
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'space-between',
    zIndex: 1,
    pb: 2
  },
  headlineLeft: {
    display: 'flex',
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'flex-start',
    gap: theme.spacing(1)
  }
});

export default function HeadlinePage({
  title = '',
  buttonLabel = '',
  handleOnClick=null,
  goBack=null
}) {
  const [t] = useTranslation();
  const theme = useTheme();
  const styles = useStyle(theme);

  return (
    <Box sx={styles.headline} component="div">
      <Box sx={styles.headlineLeft}>
        { (goBack && typeof goBack === "function") ?
          (
            <IconButton aria-label="back" color='primary' onClick={goBack}>
              <ArrowBackIcon />
            </IconButton>
          ): typeof goBack === "string" && (
            <Link to={goBack}>
              <IconButton aria-label="back" color='primary'>
                <ArrowBackIcon />
              </IconButton>
            </Link>
          )
        }
        <Typography variant="h6" id="headlinePage">
          {t(title)}
        </Typography>
      </Box>
      { (buttonLabel && handleOnClick) &&
        <Button onClick={handleOnClick} variant="outlined">
          {t(buttonLabel)}
        </Button>
      }
    </Box>
  );
}
