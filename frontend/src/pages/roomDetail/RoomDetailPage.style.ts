import styled from "styled-components";
import media from "@/styles/media";

export const Layout = styled.div`
  display: flex;
  flex-direction: column;
  gap: 5rem;

  ${media.small`
    gap: 2rem;
  `}
`;

export const FeedbackContainer = styled.div`
  display: flex;
  gap: 2rem;
  justify-content: space-between;

  ${media.small`
    display: flex;
    gap: 2rem;  
    flex-direction: column;
  `}
`;

export const FeedbackSection = styled.div`
  width: 100%;

  ${media.medium`
    width: calc(50% - 1rem); 
  `}

  ${media.large`
    width: calc(50% - 1rem); 
  `}
`;

export const StyledDescription = styled.p`
  font: ${({ theme }) => theme.TEXT.small};
  color: ${({ theme }) => theme.COLOR.grey3};
`;

export const ExpandableSection = styled.div<{ $isExpanded: boolean }>`
  cursor: pointer;
  overflow: hidden;
  transition: all 0.3s ease-in-out;
`;

export const StyledSquare = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;

  padding: 1rem;

  font: ${({ theme }) => theme.TEXT.small};
  color: ${({ theme }) => theme.COLOR.black};

  background-color: ${({ theme }) => theme.COLOR.primary1};
  border-left: 4px solid ${({ theme }) => theme.COLOR.primary3};
`;

export const ExpandableContent = styled.div`
  padding: 1rem;

  font: ${({ theme }) => theme.TEXT.small};
  color: ${({ theme }) => theme.COLOR.black};

  background-color: ${({ theme }) => theme.COLOR.grey0};
  border-left: 4px solid ${({ theme }) => theme.COLOR.primary3};

  transition: all 0.3s ease-in-out;

  p {
    margin-bottom: 1rem;
  }

  ol {
    padding-left: 1rem;
  }

  li {
    margin: 0 0 0.4rem 1rem;
    list-style-type: auto;
  }
`;

export const ToggleWrapper = styled.div`
  display: flex;
  flex-direction: column;
  gap: 1rem;
`;
