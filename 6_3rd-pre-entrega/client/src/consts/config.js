export const API_HOST = process.env.API_HOST || "http://localhost:8000";
export const LANGUAGE = process.env.LANGUAGE || "en";
export const PORT = process.env.PORT || 8000;
export const AUTH_ENABLED = JSON.parse(process.env.AUTH_ENABLED || true) || true;
