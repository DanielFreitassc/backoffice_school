import { IUser, IUserPost, TUserPost } from "../../utils/types";
import { IHttpClient } from "../types";

export class UserService {
  private httpClient: IHttpClient;

  constructor(HttpClient: IHttpClient) {
    this.httpClient = HttpClient;
  }

  async getUserList({ role }: { role: string }): Promise<IUser[]> {
    try {
      const res = await this.httpClient.get<IUser[]>(`/users?role=${role}`);
      return res.data;
    } catch (err) {
      console.log(err);
      return [];
    }
  }

  async post(user: TUserPost): Promise<TUserPost | void> {
    try {
      const res = await this.httpClient.post<TUserPost>("/user", user);
      return res.data;
    } catch (err) {
      console.log(err);
    }
  }

  // async delete(id: string): Promise<TUserPost> {
  //   try {
  //     const res = await this.httpClient.delete<IUserPost>(`/users/${id}`);
  //     return res.data;
  //   } catch (err) {
  //     console.log(err);
  //   }
  // }

  async update(user: IUserPost): Promise<TUserPost | void> {
    try {
      const res = await this.httpClient.update<TUserPost>("/users", user);
      return res.data;
    } catch (err) {
      console.log(err);
    }
  }
}
