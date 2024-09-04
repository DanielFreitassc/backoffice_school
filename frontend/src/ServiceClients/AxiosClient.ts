import { AxiosResponse } from "axios";
import { IHttpResponse, IHttpClient } from "../services/types";
import { api } from "../services/api";

export const AxiosClient: IHttpClient = {
  async get<T>(url: string): Promise<IHttpResponse<T>> {
    const res: AxiosResponse<T> = await api.get(url);

    return {
      data: res.data,
      status: res.status,
    };
  },

  async post<T, K>(url: string, body: K): Promise<IHttpResponse<T>> {
    const res: AxiosResponse<T> = await api.post(url, body);

    return {
      data: res.data,
      status: res.status,
    };
  },

  async delete<T>(url: string){
    const res: AxiosResponse<T> = await api.delete(url);

    return {
      data: res.data,
      status: res.status
    }
  },

  async put<T, K>(url: string, body: K): Promise<IHttpResponse<T>>{
      const res: AxiosResponse<T> = await api.put(url, body);
      
      return {
        data: res.data,
        status: res.status
      }
  },
  
  async patch<T, K>(url: string, body: K): Promise<IHttpResponse<T>>{
    const res: AxiosResponse<T> = await api.patch(url, body);
    
    return {
      data: res.data,
      status: res.status
    }
  }
};
