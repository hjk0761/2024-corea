import { ReviewerInfo } from "../@types/reviewer";
import apiClient from "./apiClient";
import { API_ENDPOINTS } from "./endpoints";
import MESSAGES from "@/constants/message";

export const getMyReviewers = async (roomId: number): Promise<ReviewerInfo[]> => {
  const res = await apiClient.get({
    endpoint: API_ENDPOINTS.REVIEWERS(roomId),
    errorMessage: MESSAGES.ERROR.GET_MY_REVIEWERS,
  });

  return res.matchResultResponses;
};

export const getMyReviewees = async (roomId: number): Promise<ReviewerInfo[]> => {
  const res = await apiClient.get({
    endpoint: API_ENDPOINTS.REVIEWEES(roomId),
    errorMessage: MESSAGES.ERROR.GET_MY_REVIEWEES,
  });

  return res.matchResultResponses;
};

export const postReviewComplete = async (roomId: number, revieweeId: number): Promise<void> => {
  const res = await apiClient.post({
    endpoint: API_ENDPOINTS.REVIEW_COMPLETE,
    body: {
      roomId,
      revieweeId,
    },
    errorMessage: MESSAGES.ERROR.POST_PARTICIPATE_IN,
  });
};
