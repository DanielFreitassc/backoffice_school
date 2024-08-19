import React, { useContext, useEffect, useState } from "react";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import { api } from "../../services/api";

// CONTEXTS
import { AuthContext } from "./AuthContext";

// COMPONENTS

// TYPES
import { Spinner } from "@chakra-ui/react";

const Container = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  min-height: 100vh;
`;

export const RequireAuth = ({ children }: { children: React.ReactNode }) => {
  const { signIn, signOut } = useContext(AuthContext);
  const [loading, setLoading] = useState<boolean>(false);
  const navigate = useNavigate();

  const validateToken = async () => {
    await api
      .get("/validation")
      .then(() => {
        const token = localStorage.getItem("authToken");
        signIn(token);
        setLoading(false);
      })
      .catch(() => {
        signOut();
        navigate("/login");
      });
  };

  useEffect(() => {
    if (localStorage.getItem("authToken")) {
      validateToken();
    } else {
      navigate("/login");
    }
  }, []);

  return loading ? (
    <Container>
      <Spinner label="Verificando credenciais" />
    </Container>
  ) : (
    children
  );
};
