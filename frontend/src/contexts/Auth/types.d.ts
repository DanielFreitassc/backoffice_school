import { JwtPayload } from "jwt-decode"

export interface IAuthContext {
    user: JwtPayload | null
    signIn: (token: string | null) => void
    signOut: () => void
  }
  