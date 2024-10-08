import { IHttpResponse, IHttpClient } from "../services/types";

export const FetchClient: IHttpClient = {
  //   getToken(): string {
  //     const token = localStorage.getItem("authToken");

  //     return token || "";
  //   },

  async get<T>(url: string): Promise<IHttpResponse<T>> {
    // const token = this.getToken ? this.getToken() : "";

    const res = await fetch(url, {
      //   headers: {
      //     Authorization: `Bearer ${token}`,
      //   },
    });
    const data = await res.json();
    return {
      data,
      status: res.status,
    };
  },

  async post<T, K>(url: string, body: K): Promise<IHttpResponse<T>> {
    const res = await fetch(url, {
      method: "POST",
      body: JSON.stringify(body),
      headers: {
        "Content-Type": "application/json",
      },
    });

    const data = await res.json();

    return {
      data,
      status: res.status,
    };
  },

  async put<T, K>(url: string, body: K): Promise<IHttpResponse<T>>{
    const res = await fetch(url, {
      method: "PUT",
      body: JSON.stringify(body),
      headers: {
        "Content-Type": "application/json",
      },
    });

    const data = await res.json();

    return {
      data: data,
      status: res.status
    }
},

  async patch<T, K>(url: string, body: K): Promise<IHttpResponse<T>>{
    const res = await fetch(url, {
      method: "PATCH",
      body: JSON.stringify(body),
      headers: {
        "Content-Type": "application/json",
      },
    });

    const data = await res.json();

    return {
      data: data,
      status: res.status
    }
  },

  async delete(url: string){
     const res = await fetch(url, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
      },
    });

    const data = await res.json();

    return {
      data: data,
      status: res.status
    }
  },
};
