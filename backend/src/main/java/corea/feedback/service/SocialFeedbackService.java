package corea.feedback.service;

import corea.exception.CoreaException;
import corea.exception.ExceptionType;
import corea.feedback.domain.SocialFeedback;
import corea.feedback.dto.SocialFeedbackRequest;
import corea.feedback.dto.SocialFeedbackResponse;
import corea.feedback.repository.SocialFeedbackRepository;
import corea.matchresult.domain.MatchResult;
import corea.matchresult.repository.MatchResultRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SocialFeedbackService {

    private static final Logger log = LogManager.getLogger(SocialFeedbackService.class);

    private final SocialFeedbackRepository socialFeedbackRepository;
    private final MatchResultRepository matchResultRepository;

    @Transactional
    public SocialFeedbackResponse create(long roomId, long deliverId, SocialFeedbackRequest request) {
        validateAlreadyExist(roomId, deliverId, request.receiverId());
        log.info("소설 피드백 작성[작성자({}), 요청값({})", deliverId, request);

        MatchResult matchResult = matchResultRepository.findByRoomIdAndReviewerIdAndRevieweeId(roomId, request.receiverId(), deliverId)
                .orElseThrow(() -> new CoreaException(ExceptionType.NOT_MATCHED_MEMBER));
        matchResult.revieweeCompleteFeedback();

        SocialFeedback feedback = saveSocialFeedback(roomId, request, matchResult);
        return SocialFeedbackResponse.from(feedback);
    }

    private void validateAlreadyExist(long roomId, long deliverId, long receiverId) {
        if (socialFeedbackRepository.existsByRoomIdAndDeliverIdAndReceiverId(roomId, deliverId, receiverId)) {
            throw new CoreaException(ExceptionType.ALREADY_COMPLETED_FEEDBACK);
        }
    }

    private SocialFeedback saveSocialFeedback(long roomId, SocialFeedbackRequest request, MatchResult matchResult) {
        SocialFeedback feedback = request.toEntity(roomId, matchResult.getReviewee(), matchResult.getReviewer());
        return socialFeedbackRepository.save(feedback);
    }

    @Transactional
    public SocialFeedbackResponse update(long feedbackId, long deliverId, SocialFeedbackRequest request) {
        log.info("소설 피드백 업데이트[작성자({}), 피드백 ID({}), 요청값({})", deliverId, feedbackId, request);

        SocialFeedback feedback = socialFeedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new CoreaException(ExceptionType.FEEDBACK_NOT_FOUND));
        updateFeedback(feedback, request);

        return SocialFeedbackResponse.from(feedback);
    }

    private void updateFeedback(SocialFeedback feedback, SocialFeedbackRequest request) {
        feedback.update(
                request.evaluationPoint(),
                request.feedbackKeywords(),
                request.feedbackText()
        );
    }

    public SocialFeedbackResponse findSocialFeedback(long roomId, long deliverId, String username) {
        SocialFeedback feedback = socialFeedbackRepository.findByRoomIdAndDeliverIdAndReceiverUsername(roomId, deliverId, username)
                .orElseThrow(() -> new CoreaException(ExceptionType.FEEDBACK_NOT_FOUND));

        return SocialFeedbackResponse.from(feedback);
    }
}
