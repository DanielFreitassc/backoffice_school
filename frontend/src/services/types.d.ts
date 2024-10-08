export interface IHttpResponse<T> {
  data: T;
  status: number;
}

export interface IHttpClient {
  get<T>(url: string): Promise<IHttpResponse<T>>;
  post<T>(url: string, body: K): Promise<IHttpResponse<T>>;
  put<T>(url: string, body: K): Promise<IHttpResponse<T>>;
  patch<T>(url: string, body: K): Promise<IHttpResponse<T>>;
  delete<T>(url: string): Promisse<IHttpResponse<T>>
  //   getToken?: () => string;
}
