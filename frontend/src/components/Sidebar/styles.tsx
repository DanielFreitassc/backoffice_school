import styled, { css } from "styled-components";
import { theme } from "../../styles/theme";

export const Container = styled.div`
  display: flex;
`;

export const SidebarContainer = styled.aside`
  position: fixed;
  top: 0;
  height: 100vh;
  width: 100px;
  background-color: ${theme.darkBrown};
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
  padding: 0 10px;
  box-shadow: 1px 1px 10px rgba(1, 1, 1, 0.3);
`;

export const LogoContainer = styled.div`
  padding: 20px 0;
`;

export const OptionsContainer = styled.div`
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  gap: 20px;
`;

export const OneOptionContainer = styled.div<{ $isActive: boolean }>`
  opacity: 0.4;
  display: flex;
  flex-direction: column;
  align-items: center;

  ${({ $isActive }) =>
    $isActive &&
    css`
      opacity: 1;
    `}

  &:hover {
    opacity: 1;
    transition: 0.5s;
    cursor: pointer;
  }
`;

export const Content = styled.div`
  margin: 0 10px 0 110px;
  width: 100%;
`;
