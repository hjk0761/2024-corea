package corea.matching.strategy;

import config.ServiceTest;
import corea.fixture.MemberFixture;
import corea.fixture.RoomFixture;
import corea.member.domain.Member;
import corea.member.domain.MemberRole;
import corea.member.repository.MemberRepository;
import corea.participation.domain.Participation;
import corea.room.domain.Room;
import corea.room.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

@ServiceTest
class MatchingStrategyTest {

    @Autowired
    private MatchingStrategy matchingStrategy;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MemberRepository memberRepository;

    private Room room;
    private Member manager;
    private Member joyson;
    private Member pororo;

    private List<Participation> participations;

    @BeforeEach
    void setUp() {
        manager = memberRepository.save(MemberFixture.MEMBER_ROOM_MANAGER_JOYSON());
        joyson = memberRepository.save(MemberFixture.MEMBER_YOUNGSU());
        pororo = memberRepository.save(MemberFixture.MEMBER_PORORO());

        room = roomRepository.save(RoomFixture.ROOM_DOMAIN(manager));
        participations = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            participations.add(new Participation(room, joyson, MemberRole.BOTH, room.getMatchingSize()));
            participations.add(new Participation(room, pororo, MemberRole.BOTH, room.getMatchingSize()));
        }
    }

    @Test
    @DisplayName("인원 수가 매칭 사이즈보다 큰 경우 매칭을 수행한다.")
    void matchMaking() {
        int matchingSize = 3;

        assertThatCode(() -> matchingStrategy.matchPairs(participations, matchingSize))
                .doesNotThrowAnyException();
    }
}
