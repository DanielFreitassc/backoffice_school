export interface IUser {
  id: string;
  name: string;
  username: string;
  role: string;
}

interface IUserPost extends IUser {
  password: string;
}

export type TUserPost = Omit<IUserPost, "id">;
export type TUserPut = Omit<IUserPost, "id", "password">;

export interface ISelect {
  label: string;
  value: string;
}

export enum RoleEnum {
  ALUNO = "ALUNO",
  PROFESSOR = "PROFESSOR",
  ADMIN = "ADMIN",
}
