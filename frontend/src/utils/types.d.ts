export interface IUser {
  id: string;
  name: string;
  username: string;
  role: string;
  createdAt: string;
}

interface IUserPost extends IUser {
  password: string;
}

export type TUserPost = Omit<IUserPost, "id" | "createdAt">;
export type TUserPut = Omit<IUserPost, "id" | "password" | 'createdAt'>;

export interface ISelect {
  label: string;
  value: string;
}

export enum RoleEnum {
  ALUNO = "ALUNO",
  PROFESSOR = "PROFESSOR",
  ADMIN = "ADMIN",
}
