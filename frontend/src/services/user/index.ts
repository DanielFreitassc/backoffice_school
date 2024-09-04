import { AxiosClient } from "../../ServiceClients/AxiosClient";
import { IUser, IUserPost, TUserPost, TUserPut } from "../../utils/types";
import { IHttpClient } from "../types";

export class UserService {
  private httpClient: IHttpClient = AxiosClient;

  async getUserList(): Promise<IUser[]> {
    try {
      const res = await this.httpClient.get<IUser[]>(`/user`);
      return res.data;
    } catch {
      return [];
    }
  }

  async post(user: TUserPost): Promise<TUserPost> {
    const res = await this.httpClient.post<TUserPost>("/user", user);
    return res.data;
  }

  async delete(username: string): Promise<TUserPost> {
    const res = await this.httpClient.delete<IUserPost>(`/user/${username}`);
    return res.data;
  }

  async update(user: TUserPut): Promise<TUserPut> {
      const res = await this.httpClient.patch<TUserPost>(`/user/${user.username}`, user);
      return res.data;
  }
}
