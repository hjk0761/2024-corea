import styled from "styled-components";
import media from "@/styles/media";

export const GuidPageLayout = styled.div`
  display: flex;
  flex-direction: column;
  gap: 5rem;
`;

export const GuideContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 2rem;
`;

export const CardContainer = styled.div`
  cursor: pointer;

  display: flex;
  gap: 0.4rem;
  align-items: center;
  justify-content: center;

  margin: 0 auto;
  padding: 0.5rem 1rem;

  background-color: ${({ theme }) => theme.COLOR.primary1};
  border: 2px solid ${({ theme }) => theme.COLOR.grey1};
  border-radius: 12px;
  box-shadow: 0 4px 4px rgb(0 0 0 / 10%);

  &:hover {
    ${media.medium`
      transform: scale(1.05);
    `}
    ${media.large`
      transform: scale(1.05);
    `}
  }

  span {
    font: ${({ theme }) => theme.TEXT.small};
  }
`;

export const StyledContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 1rem;

  margin-bottom: 2rem;

  font: ${({ theme }) => theme.TEXT.small};
  line-height: 1.2rem;

  h1 {
    margin-top: 1rem;
    font: ${({ theme }) => theme.TEXT.large};
  }

  h2 {
    margin-top: 1rem;
    font: ${({ theme }) => theme.TEXT.medium};
  }

  h3 {
    font: ${({ theme }) => theme.TEXT.small};
  }

  img {
    width: 100%;
  }

  img.medium {
    width: 70%;
  }

  li {
    margin: 0 0 0.4rem 1rem;
    list-style-type: initial;
  }

  li.second {
    margin-left: 2rem;
  }

  li.third {
    margin-left: 3rem;
  }

  em {
    font-style: italic;
  }
`;

export const StyledSquare = styled.div`
  padding: 1rem;
  background-color: ${({ theme }) => theme.COLOR.grey0};
  border-left: 4px solid ${({ theme }) => theme.COLOR.primary3};
`;

export const LinkWrapper = styled.div`
  display: inline;

  a {
    font: ${({ theme }) => theme.TEXT.small};
    color: ${({ theme }) => theme.COLOR.primary2};
    text-decoration: underline;
  }

  p {
    font: ${({ theme }) => theme.TEXT.small};
    color: ${({ theme }) => theme.COLOR.black};
    text-decoration: none;
  }
`;

export const IconWrapper = styled.div`
  display: flex;
  gap: 0.4rem;
  align-items: center;

  a {
    font: ${({ theme }) => theme.TEXT.small};
    color: ${({ theme }) => theme.COLOR.primary2};
    text-decoration: underline;
  }
`;

export const StyledPre = styled.pre`
  overflow-x: auto;

  padding: 1rem;

  line-height: 1.2rem;

  background-color: ${({ theme }) => theme.COLOR.black};
  border-radius: 8px;
`;

export const StyledCode = styled.code`
  font: ${({ theme }) => theme.TEXT.small};
  color: ${({ theme }) => theme.COLOR.white};
`;
