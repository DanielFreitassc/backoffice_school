import { useState } from "react";
import { jwtDecode, JwtPayload } from "jwt-decode";
import { AuthContext } from "./AuthContext";

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
  const [user, setUser] = useState<JwtPayload | null>(null);

  const signOut = () => {
    setUser(null);
    localStorage.removeItem("authToken");
  };

  const signIn = async (token: string | null) => {
    if (!token) return;
    const decoded = jwtDecode(token);
    console.log(decoded);

    localStorage.setItem("authToken", token);

    setUser(decoded);
    console.log("a");
  };

  return (
    <AuthContext.Provider value={{ user, signIn, signOut }}>
      {children}
    </AuthContext.Provider>
  );
};
