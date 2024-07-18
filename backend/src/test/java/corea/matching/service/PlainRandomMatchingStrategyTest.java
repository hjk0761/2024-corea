package corea.matching.service;

import corea.matching.domain.PlainRandomMatching;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PlainRandomMatchingStrategyTest {

    private final PlainRandomMatching plainRandomMatching = new PlainRandomMatching();

    @Test
    @DisplayName("아이디의 리스트가 들어오면 매칭 사이즈 만큼 그룹을 나눈다.")
    void matchGroup() {
        List<Long> memberIds = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L);
        int matchingSize = 3;

        Map<Long, List<Long>> result = plainRandomMatching.matchGroup(memberIds, matchingSize);

        assertThat(result).hasSize(2);
    }
}
