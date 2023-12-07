import i18n from "i18next";
import { initReactI18next } from "react-i18next";
import { LANGUAGE_REMEMBER, LANGUAGE } from '~/consts';
import { en } from '~/language';
import moment from 'moment';
import 'moment/locale/es';


const resources = {
	en: {
		translation: en,
	}
};

const defaultLanguage = LANGUAGE;
moment.locale(localStorage.getItem(LANGUAGE_REMEMBER) || defaultLanguage || 'en');

i18n.use(initReactI18next).init({
  resources,
  lng: localStorage.getItem(LANGUAGE_REMEMBER) || defaultLanguage || 'en',
  fallbackLng: {
		default: ['en']
	},
	interpolation: {
		escapeValue: false,
		format: (value, format, lng) => {
			if (format === 'currentDate') return
			moment(value).locale('en').format('LL');
			return value;
		},
	},
  debug: false
});
