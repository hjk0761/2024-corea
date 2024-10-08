import * as S from "./FeedbackCardList.style";
import React, { useEffect, useState } from "react";
import Carousel from "@/components/common/carousel/Carousel";
import FeedbackCard from "@/components/feedback/feedbackCard/FeedbackCard";
import { FeedbackCardDataList } from "@/@types/feedback";

interface FeedbackCardListProps {
  feedbackData: FeedbackCardDataList[];
}

const FeedbackCardList = ({ feedbackData }: FeedbackCardListProps) => {
  const [selectedFeedback, setSelectedFeedback] = useState<number>();

  const handleSelectedFeedback = (roomId: number) => {
    if (selectedFeedback === roomId) {
      setSelectedFeedback(undefined);
      return;
    }

    setSelectedFeedback(roomId);
  };

  useEffect(
    function resetSelectedFeedback() {
      setSelectedFeedback(undefined);
    },
    [feedbackData],
  );

  return (
    <S.FeedbackCardContainer>
      {feedbackData.map((feedback) => (
        <React.Fragment key={feedback.roomId}>
          <S.FeedbackMissionWrapper
            $isSelected={selectedFeedback === feedback.roomId}
            onClick={() => handleSelectedFeedback(feedback.roomId)}
          >
            <S.FeedbackMissionTitle>
              <S.FeedbackMissionInfo>{feedback.title}</S.FeedbackMissionInfo>
              <S.FeedbackKeywordContainer>
                {feedback.roomKeywords.map((keyword) => (
                  <S.FeedbackKeywordWrapper key={keyword}>#{keyword}</S.FeedbackKeywordWrapper>
                ))}
              </S.FeedbackKeywordContainer>
            </S.FeedbackMissionTitle>
            <S.FeedbackMissionPrompt $isSelected={selectedFeedback === feedback.roomId}>
              피드백을 보려면 클릭해주세요
            </S.FeedbackMissionPrompt>
          </S.FeedbackMissionWrapper>
          <S.FeedbackInfoWrapper $isVisible={feedback.roomId === selectedFeedback}>
            {feedback.roomId === selectedFeedback && (
              <Carousel>
                {feedback.developFeedback.map((developFeedback) => (
                  <FeedbackCard
                    key={developFeedback.feedbackId}
                    feedbackCardData={developFeedback}
                    feedbackType="develop"
                  />
                ))}
                {feedback.socialFeedback.map((developFeedback) => (
                  <FeedbackCard
                    key={developFeedback.feedbackId}
                    feedbackCardData={developFeedback}
                    feedbackType="social"
                  />
                ))}
              </Carousel>
            )}
          </S.FeedbackInfoWrapper>
        </React.Fragment>
      ))}
    </S.FeedbackCardContainer>
  );
};

export default FeedbackCardList;
