export const API_HOST = process.env.API_HOST || "http://localhost:8080";
export const API_HOST_WEBSOCKET = process.env.API_HOST_WEBSOCKET || "ws://127.0.0.1:7071/ws";
export const LANGUAGE = process.env.LANGUAGE || "es";
export const AUTH_ENABLED = JSON.parse(process.env.AUTH_ENABLED || true) || true;
