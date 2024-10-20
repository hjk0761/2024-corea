package corea.feedback.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "개발 능력 관련 피드백 업데이트 요청")
public record DevelopFeedbackUpdateInput(@Schema(description = "평가 점수", example = "4")
                                         int evaluationPoint,

                                         @Schema(description = "선택한 피드백 키워드", example = "[\"코드를 이해하기 쉬웠어요\", \"컨벤션이 잘 지켜졌어요\"]")
                                         List<String> feedbackKeywords,

                                         @Schema(description = "부가 작성 가능한 피드백 텍스트", example = "처음 자바를 접해봤다고 했는데 생각보다 매우 잘 구성되어 있는 코드였습니다. ...")
                                         String feedbackText,

                                         @Schema(description = "랭킹에 필요한 추천 점수", example = "2")
                                         int recommendationPoint) {
}
