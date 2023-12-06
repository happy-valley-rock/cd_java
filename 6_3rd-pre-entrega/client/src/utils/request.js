import Axios from "axios";


const setUpAxiosInstance = ({ baseURL, getToken }) => {
	const axiosInstance = Axios.create({ baseURL: baseURL });
	axiosInstance.interceptors.request.use(
		async (config) => {
			if (config.baseURL?.includes("authentication")) return config;
			const accessToken = getToken();

			if (accessToken && config.method !== "OPTIONS") {
				config.headers.Authorization = `Bearer ${accessToken}`;
			}
			return config;
		},
		(error) => {
			return Promise.reject(error);
		}
	);

	const getRequest = async (url, headers) => await axiosInstance.get(url, headers);

	const postRequest = async (url, body) => await axiosInstance.post(url, body);

	const deleteRequest = async (url) => await axiosInstance.delete(url);

	const putRequest = async (url, body) => await axiosInstance.put(url, body);

	return { axiosInstance, getRequest, postRequest, deleteRequest, putRequest };
};

export default setUpAxiosInstance;
