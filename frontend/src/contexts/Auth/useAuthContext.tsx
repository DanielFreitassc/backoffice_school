import { useContext } from "react"
import { AuthContext } from "./AuthContext"

export const useAuthProvider = () => {
    const authContext = useContext(AuthContext)

    return authContext
}