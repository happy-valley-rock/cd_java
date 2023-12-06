import {useState, useEffect} from "react";
import {API_HOST_WEBSOCKET} from '~/consts';


export default function useWSStore() {
    const [clients, setClients] = useState([]);

    useEffect(() => {
        let wsInstance = null;
        let isMounted = true;

        const connectWebSocket = () => {
            wsInstance = new WebSocket(API_HOST_WEBSOCKET);

            wsInstance.onopen = () => {
                wsInstance.send(JSON.stringify({ id: 'tflow-client-app' }));
            };

            wsInstance.onmessage = (event) => {
                setClients(JSON.parse(event.data))
            };

            wsInstance.onerror = (error) => {
                setClients([])
            };

            wsInstance.onclose = () => {
                if (isMounted) {
                    setTimeout(connectWebSocket, 2000);
                }
            };
        };

        connectWebSocket();

        return () => {
            isMounted = false;
            if (wsInstance) {
                wsInstance.close();
            }
        };
    }, []);

    return {
        clients
    }
}
