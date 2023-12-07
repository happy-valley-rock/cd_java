import React from 'react';
import { Snackbar } from '@mui/material';
import MuiAlert from '@mui/material/Alert';
import { useTranslation } from 'react-i18next';


const CustomAlert = React.forwardRef(function Alert(props, ref) {
  return <MuiAlert
    elevation={6}
    ref={ref}
    variant="filled"
    {...props}
  />;
});

export default function Alert(props) {
  const {
    severity,
    handleClose,
    open,
    message,
    delay=4000
  } = props;
  const [t] = useTranslation();

  return (
    <Snackbar
      open={!!open}
      autoHideDuration={delay}
      onClose={handleClose}
      anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
      sx={{ width: '50%', mt: 8 }}
    >
      <CustomAlert onClose={handleClose} severity={severity} sx={{ width: '100%' }}>
        {t(message)}
      </CustomAlert>
    </Snackbar>
  );
}
