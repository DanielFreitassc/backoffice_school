import styled from "styled-components";
import { theme } from "../../styles/theme";

export const Container = styled.div`
  height: 100vh;
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 20px;
  background-color: ${theme.darkBrown};
`;

export const LogoContainer = styled.div`
  padding: 20px;
`;

export const ContentContainer = styled.div`
  padding: 10px;
  max-width: 450px;
  min-width: 280px;
  width: 100%;
`;

export const LoginContainer = styled.div`
  background-color: white;
  padding: 20px;
  border-radius: 10px;
`;

export const FormContainer = styled.form`
  display: flex;
  flex-direction: column;
  gap: 20px;
  margin-top: 20px;
`;
