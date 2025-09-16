import axios from "axios";
import { useUserStore } from '../stores/user';

const axiosClient = axios.create({
    baseURL: 'http://localhost:8080/api/'
});

axiosClient.interceptors.request.use((config) => {
    const userStore = useUserStore();
    config.headers.Authorization = `Bearer ${userStore.getUser?.token ?? ''}`;
    config.headers.Accept = "application/json";
    return config
})

export default axiosClient;