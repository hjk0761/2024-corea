package corea.matching.domain;

import java.util.List;
import java.util.Map;

public interface MatchingStrategy {

    Map<Long, Long> matchPairs(List<Long> memberIds, int matchingSize);
}
