import { grey } from '@mui/material/colors';
import { alpha } from '@mui/material/styles';
import { jungle, asphalte, ghost } from '~/assets/colors';
import { shadows } from '@mui/system';


export const getDesignTokens = (mode) => ({
  palette: {
    mode,
    ...(mode === 'light'
      ? {
          // palette values for light mode
          primary: jungle,
          secondary: asphalte,
          text: {
            primary: grey[900],
            secondary: grey[800],
          },
          custom: {
            light: ghost,
          },
          common: {
            black: asphalte[500]
          }
        }
      : {
          // palette values for dark mode
          primary: jungle,
          divider: asphalte[700],

        }),
  },
  shape: {
    borderRadius: 16,
  },
  typography: {
    fontFamily: 'Poppins, sans-serif'
  },
  components: {
    // MuiTableHead: {
    //   styleOverrides: {
    //     root: {
    //       th: {
    //         fontWeight: 600,
    //       },
    //     },
    //   },
    // },
    MuiPaper: {
      styleOverrides: {
        root: {
          boxShadow: '0 19px 38px rgba(0,0,0,0.030), 0 15px 12px rgba(0,0,0,0.022)'
        },
      },
    },
    MuiToolbar: {
      styleOverrides: {
        root: {
          backgroundColor: 'white',
          boxShadow: 'none'
        },
      },
    },
    MuiAppBar: {
      styleOverrides: {
        root: {
          backgroundColor: 'white',
          boxShadow: 'none'
        }
      },
    },
    MuiDrawer: {
      styleOverrides: {
        borderColor: 'red',
        paper: {
          color: asphalte[500],
          hr: {
            backgroundColor: alpha(grey['50'], 0.2),
            margin: 8
          },
          a: {
            borderRadius: 8,
            textDecoration: 'none',
            color: asphalte[500],

            '&.active, &.active svg': {
              color: jungle[500]
            },
            '&.active li': {
              background: alpha(jungle[500], 0.15),
            },
            '&:hover, &:hover svg': {
              color: jungle[500],
            },
          },
          li: {
            borderRadius: 8,
            overflow: 'hidden',
            '&:hover': {
              // background: alpha(jungle[500], 0.15),
            },
          },
          svg: {
            color: asphalte[500],
          },
        },
      },
    },
    MuiButton: {
      styleOverrides: {
        root: {
          borderRadius: '8px'
        },
        contained: {
          color: 'white',
          '&:disabled': {
            background: grey['300']
          }
        }
      }
    }
  },
});
