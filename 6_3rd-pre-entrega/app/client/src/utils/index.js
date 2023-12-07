import ConfigureCredentialHandler from './credentials';
import ConfigureRequestHandler from './request';
import ConfigureServicesHandler from './services';
import Validations from './validations';
import { TOKEN, REMEMBER_ME, API_HOST, REFRESH_TOKEN } from '~/consts';
import './translation';

const validations = new Validations();

const credentialsHandlers = ConfigureCredentialHandler({
  TOKEN_KEY: TOKEN,
  REMEMBER_ME_KEY: REMEMBER_ME,
  REFRESH_TOKEN_KEY: REFRESH_TOKEN
});

const requestHandlers = ConfigureRequestHandler({
  baseURL: API_HOST,
  getToken: credentialsHandlers.getToken
});

const servicesHandlers = ConfigureServicesHandler({ ...requestHandlers, validations });

const credentials = credentialsHandlers;
const request = requestHandlers;
const services = servicesHandlers;

Object.freeze(credentials);
Object.freeze(request);
Object.freeze(services);

export { credentials };
export { request };
export { services };
export { validations };

export default {
  credentials,
  request,
  services,
  validations
};
