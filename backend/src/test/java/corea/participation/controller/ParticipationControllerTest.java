package corea.participation.controller;

import config.ControllerTest;
import corea.auth.domain.TokenGenerator;
import corea.auth.repository.AuthenticationAuthorizationInfoRepository;
import corea.auth.service.AuthenticationAuthorizationService;
import corea.fixture.MemberFixture;
import corea.fixture.RoomFixture;
import corea.member.domain.Member;
import corea.member.repository.MemberRepository;
import corea.room.domain.Room;
import corea.room.repository.RoomRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@ControllerTest
class ParticipationControllerTest {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private AuthenticationAuthorizationService authenticationAuthorizationService;

    @Test
    @DisplayName("사용자가 방에 참여한다.")
    void participate() {
        Member manager = memberRepository.save(MemberFixture.MEMBER_JOYSON());
        Room room = roomRepository.save(RoomFixture.ROOM_DOMAIN(manager));
        Member member = memberRepository.save(MemberFixture.MEMBER_PORORO());
        String token = authenticationAuthorizationService.createAccessToken(member);

        RestAssured.given().log().all()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .when().post("/participate/" + room.getId())
                .then().log().all()
                .statusCode(200);
    }
}
