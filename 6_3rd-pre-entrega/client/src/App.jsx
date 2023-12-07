import { createContext, useState, useEffect, useMemo } from 'react';
import "typeface-poppins";
import { Provider } from 'react-redux';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import { CircularProgress, Backdrop } from '@mui/material';
import { getDesignTokens } from './theme.js';
import store from '~/store';
import { RoutesProvider } from '~/routes';
import { ErrorBoundary } from '~/components';
import { GlobalAlert } from '~/components/container';


const ColorModeProvider = createContext({ toggleColorMode: () => {} }).Provider;

function App() {
  const [isLoading, setIsLoading] = useState(true);
  const [mode, setMode] = useState('light');
  const colorMode = useMemo(
    () => ({
      toggleColorMode: () => setMode((prevMode) => prevMode === 'light' ? 'dark' : 'light')
    }),
    [],
  );

  const theme = useMemo(() => createTheme(getDesignTokens(mode)), [mode]);

  useEffect(() => {
    setIsLoading(false);
  }, []);

  return (
    <ErrorBoundary>
      <Provider store={store} >
        <ColorModeProvider value={colorMode}>
          <ThemeProvider theme={theme}>
              <GlobalAlert />
                { isLoading ?
                  (
                    <Backdrop
                      invisible
                      sx={{ zIndex: (theme) => theme.zIndex.drawer + 1 }}
                      open={isLoading}
                    >
                      <CircularProgress color="primary" />
                    </Backdrop>
                  )
                  :(
                    <RoutesProvider />
                  )
                }
          </ThemeProvider>
        </ColorModeProvider>
      </Provider>
    </ErrorBoundary>
  )
}

export default App
