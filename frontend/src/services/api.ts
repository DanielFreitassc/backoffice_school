import axios from "axios";
import { catchHandler, thenHandler } from "../utils/functions";

export const api = axios.create({
    baseURL: import.meta.env.VITE_API_URL
})

api.interceptors.request.use((config) => {
    const token = localStorage.getItem('authToken')
    const customConfig = config

    if(token) customConfig.headers.Authorization = `Bearer ${token}`

    return customConfig
})

api.interceptors.response.use(
    (response) => {
        thenHandler(response);
        return response
    },
    (error) => {
        catchHandler(error);
        console.log(error);
        
        return 
    }
)