import * as React from 'react';
import { Paper, Button, Typography, Modal, Box, Fade } from '@mui/material';
import ReportGmailerrorredIcon from '@mui/icons-material/ReportGmailerrorred';
import { useTranslation } from 'react-i18next';


const useStyle = ((theme) => ({
  style: {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    gap: theme.spacing(3),
    display: 'flex',
    flexDirection: 'column',
    padding: theme.spacing(3),
  },
  boxModalTop: {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
  },
  boxModalBottom: {
    display: 'flex',
    gap: theme.spacing(2),
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
  },
}));

export default function ModalAlert({
  open,
  handleClose,
  handleConfirm,
  label='common.components.modal.label',
}) {
  const styles = useStyle();
  const [t] = useTranslation();

  return (
    <Modal
      sx={{ backdropFilter: 'blur(2px)' }}
      open={open}
      onClose={handleClose}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Fade in={open}>
        <Paper sx={styles.style}>
          <Box sx={styles.boxModalTop}>
            <Typography id="modal-modal-title" variant="h6" component="h2">
              {t(label)}
            </Typography>
            <ReportGmailerrorredIcon color="warning" sx={{ fontSize: 100 }} />
          </Box>

          <Box sx={styles.boxModalBottom}>
            <Button onClick={handleClose} variant="text" fullWidth>
              {t('common.components.buttons.cancel')}
            </Button>
            <Button onClick={handleConfirm} variant="outlined" fullWidth>
              {t('common.components.buttons.confirm')}
            </Button>
          </Box>
        </Paper>
      </Fade>
    </Modal>
  );
}
