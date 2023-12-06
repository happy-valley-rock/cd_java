
const setUpCredentialsHandler = ({ TOKEN_KEY, REMEMBER_ME_KEY, REFRESH_TOKEN_KEY }) => {
	const getToken = () => localStorage.getItem(TOKEN_KEY) || sessionStorage.getItem(TOKEN_KEY);
	const setToken = (value, persist) => persist ? localStorage.setItem(TOKEN_KEY, value) : sessionStorage.setItem(TOKEN_KEY, value);
	const getRefreshToken = () => localStorage.getItem(REFRESH_TOKEN_KEY) || sessionStorage.getItem(REFRESH_TOKEN_KEY);
	const setRefreshToken = (value, persist) => persist ? localStorage.setItem(REFRESH_TOKEN_KEY, value) : sessionStorage.setItem(REFRESH_TOKEN_KEY, value);
	const setRememberMe = (value) => value && localStorage.setItem(REMEMBER_ME_KEY, "keep-logged-in");
	const getRememberMe = () => localStorage.getItem(REMEMBER_ME_KEY) === "keep-logged-in";
	const removeToken = () => { localStorage.removeItem(TOKEN_KEY); sessionStorage.removeItem(TOKEN_KEY)};
	const removeRememberMe = () => { localStorage.removeItem(REMEMBER_ME_KEY)};
	const removeRefreshToken = () => { localStorage.removeItem(REFRESH_TOKEN_KEY); sessionStorage.removeItem(REFRESH_TOKEN_KEY)};

	return {
		setToken,
		setRefreshToken,
		setRememberMe,
		getToken,
		getRefreshToken,
		getRememberMe,
		removeToken,
		removeRememberMe,
		removeRefreshToken
	};
};

export default setUpCredentialsHandler;
