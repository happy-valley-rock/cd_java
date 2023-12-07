import { useTranslation } from 'react-i18next';
import { useTheme } from '@mui/material/styles';
import { Box, Typography } from "@mui/material";
import { asphalte, ghost } from '~/assets/colors';
import { VERSION } from '~/consts';


export default function Footer (props) {
  const theme = useTheme();
  const [t] = useTranslation();
  const year = new Date().getFullYear();


  return (
    <Box component='footer' sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        width: '100%'
      }}
    >
      <Box sx={{
          display: 'flex',
          flexDirection: 'row',
          padding: theme.spacing(2),
          width: '100%',
          justifyContent: 'center',
          alignItems: 'start',
          backgroundColor: asphalte[800],
          color: theme.palette.grey[200],
          gap: theme.spacing(1)
        }}
      >
        <Typography
          variant="caption"
        >
          {t('footer.title')}
        </Typography>

        <Box sx={{ height: '24px', borderRadius: '100%', overflow: 'hidden' }}>
          <a href="https://github.com/happy-valley-rock" target="_blank" rel="noopener noreferrer">
            <img src="https://avatars.githubusercontent.com/u/64182808?v=4" width="100%" height="100%"/>
          </a>
        </Box>

        <Typography variant="caption">
          {t('footer.description', { year: year })}
        </Typography>

        <Box sx={{ position: 'absolute', right: 0, pr: 2 }}>
          <Typography variant="subtitle1">{`v ${VERSION}`}</Typography>
        </Box>
      </Box>
    </Box>
  );
}
