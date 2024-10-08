import styled from "styled-components";

export const ButtonContainer = styled.button`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  width: fit-content;
  margin: 1rem 0;

  font: ${({ theme }) => theme.TEXT.small};
  color: ${({ theme }) => theme.COLOR.grey3};
  text-align: center;

  background-color: transparent;

  transition: transform 0.3s;

  &:hover {
    transform: scale(1.1);
  }
`;
