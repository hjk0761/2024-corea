import styled from "styled-components";
import media from "@/styles/media";

export const CyclingContainer = styled.div`
  overflow: hidden;
  height: 60px;

  ${media.medium`
    height: 42px;
  `}
`;

export const CyclingList = styled.ul`
  position: relative;

  display: flex;
  flex-direction: column;
  align-items: flex-end;

  width: 180px;
  height: 52px;
  padding: 1rem;

  font-family: "Do Hyeon", sans-serif;
  font-size: 8rem;

  li {
    position: absolute;
    bottom: -5rem;
    visibility: hidden;
    opacity: 0;
  }

  li.prev {
    bottom: 5rem;
    visibility: hidden;
    opacity: 1;
    transition: all 1.3s;
  }

  li.on {
    bottom: 0;
    visibility: visible;
    opacity: 1;
    transition: all 1.3s;
  }

  ${media.medium`
    width: 150px;
    height: 40px;
    font-size: 6.4rem;
  `}
`;
