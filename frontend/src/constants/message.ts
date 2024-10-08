const GUIDANCE_MESSAGES = {
  EMPTY_REVIEWER: "아직 리뷰어가 매칭되지 않았습니다! 조금만 기다려주세요🤗",
  EMPTY_REVIEWEE: "아직 리뷰이가 매칭되지 않았습니다! 조금만 기다려주세요🤗",
};

const ERROR_MESSAGES = {
  OFFLINE: "오프라인 상태입니다. 네트워크를 확인해주세요.",

  // auth
  POST_LOGIN: "로그인 도중 오류가 발생했습니다. 다시 로그인 해주세요.",
  POST_REFRESH: "토큰이 만료되었습니다. 다시 로그인 해주세요.",
  POST_LOGOUT: "로그아웃 도중 오류가 발생했습니다. 다시 로그아웃 해주세요.",

  // rooms
  GET_PARTICIPATED_ROOM_LIST: "참여하는 방 목록을 불러오는 도중 에러가 발생하였습니다.",
  GET_OPENED_ROOM_LIST: "모집 중인 방 목록을 불러오는 도중 에러가 발생하였습니다.",
  GET_CLOSED_ROOM_LIST: "모집 완료 방 목록을 불러오는 도중 에러가 발생하였습니다.",
  GET_ROOM_DETAIL_INFO: "방 상세정보를 불러오는 도중 에러가 발생하였습니다.",
  POST_PARTICIPATE_IN: "방 참여하기를 실패했습니다.",

  // reviews
  GET_MY_REVIEWERS: "리뷰어 목록을 불러오는 도중 에러가 발생하였습니다.",
  GET_MY_REVIEWEES: "리뷰이 목록을 불러오는 도중 에러가 발생하였습니다.",

  // profile
  GET_PROFILE: "프로필 불러오는 도중 에러가 발생하였습니다.",

  // feedbacks
  // 리뷰어 -> 리뷰이
  GET_REVIEWEE_FEEDBACK: "리뷰이 피드백을 불러오는 도중 에러가 발생하였습니다. ",
  POST_REVIEWEE_FEEDBACK: "리뷰이 피드백 작성에 실패했습니다.",
  PUT_REVIEWEE_FEEDBACK: "리뷰이 피드백 수정에 실패했습니다.",
  // 리뷰이 -> 리뷰어
  GET_REVIEWER_FEEDBACK: "리뷰어 피드백을 불러오는 도중 에러가 발생하였습니다. ",
  POST_REVIEWER_FEEDBACK: "리뷰어 피드백 작성에 실패했습니다.",
  PUT_REVIEWER_FEEDBACK: "리뷰어 피드백 수정에 실패했습니다.",
  //피드백 모아보기
  GET_RECEIVED_FEEDBACK: "받은 피드백 불러오는 도중 에러가 발생하였습니다.",
  GET_DELIVERED_FEEDBACK: "받은 피드백 불러오는 도중 에러가 발생하였습니다.",

  // ranking
  GET_RANKING: "랭킹을 불러오는 도중 에러가 발생하였습니다.",

  // errorBoundary
  BOUNDARY_TOTAL: "일시적인 에러가 발생했습니다.",
  BOUNDARY_API: "네트워크 에러가 발생했습니다.",
};

const SUCCESS_MESSAGES = {
  POST_REVIEW_FEEDBACK: "피드백을 작성하였습니다.",
  POST_PARTICIPATE_IN: "정상적으로 방에 참여하였습니다.",
  PUT_REVIEW_FEEDBACK: "피드백을 수정하였습니다.",
};

const MESSAGES = {
  GUIDANCE: GUIDANCE_MESSAGES,
  ERROR: ERROR_MESSAGES,
  SUCCESS: SUCCESS_MESSAGES,
};

export default MESSAGES;
