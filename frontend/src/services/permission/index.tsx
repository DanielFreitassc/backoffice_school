import { IHttpClient } from "../types";

export interface IPermissionSelect {
  name: string;
  role: string;
}

export class PermissionService {
  private httpClient: IHttpClient;

  constructor(HttpClient: IHttpClient) {
    this.httpClient = HttpClient;
  }

  async getPermissionSelect(): Promise<IPermissionSelect[]> {
    try {
      const res = await this.httpClient.get<IPermissionSelect[]>(
        "/permission/select"
      );
      return res.data;
    } catch (err) {
      console.log(err);
      return [];
    }
  }
}
