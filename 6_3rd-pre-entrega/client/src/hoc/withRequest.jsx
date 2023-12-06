import React, { useEffect } from 'react';
import { request } from "~/utils";


export default function withRequest(Component) {
  return function(props) {
    const axiosInstance = request.axiosInstance;

    const reqInterceptor = (req) => {
      if (window.config?.DEBUG && window.config.DEBUG) console.error('REQUEST', req);
    }

    const resInterceptor = axiosInstance.interceptors.response.use(
  		(response) => {
        if (response) {
          if (response?.data) {
            return Promise.resolve(response);
          } else return Promise.resolve(response);
        }
  		},
  		(error) => {
        if (error) {
          if (error.response?.data) {
            return Promise.reject(setResponse(error.response));
          } else return Promise.reject(error);
        }
  		}
  	);

    useEffect(() => {
      return () => {
        axiosInstance.interceptors.request.eject(reqInterceptor);
        axiosInstance.interceptors.response.eject(resInterceptor);
      };
      // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [resInterceptor]);

    return (
      <>
        <Component {...props} />
      </>
    )
  }
}

function setResponse(response) {
  let message = response.status + `${response.statusText && ' - ' + response.statusText}`;

  if (response.data?.message) return response.data;

  if (typeof response.data === 'object') {
    response.data['message'] = message;
  } else {
    response.data = {
      ...response.data,
      message: response.status + ' ' + response.statusText
    }
  }

  return response.data;
}
