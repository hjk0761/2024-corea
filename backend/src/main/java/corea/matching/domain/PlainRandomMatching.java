package corea.matching.domain;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PlainRandomMatching implements MatchingStrategy{

    @Override
    public Map<Long, Long> matchPairs(List<Long> memberIds, int matchingSize) {
        Map<Long, Long> result = new HashMap<>();

        List<Long> shuffledMemberIds = new ArrayList<>(memberIds);
        Collections.shuffle(shuffledMemberIds);

        result.putAll(matchReviewers(matchingSize, shuffledMemberIds));
        result.putAll(matchReviewees(matchingSize, shuffledMemberIds));

        return result;
    }

    private Map<Long, Long> matchReviewers(int matchingSize, List<Long> shuffledMemberIds) {
        Map<Long, Long> reviewerResult = new HashMap<>();
        for (int i = 0; i < shuffledMemberIds.size(); i++) {
            for (int j = 1; j < matchingSize; j++) {
                reviewerResult.put(shuffledMemberIds.get((i - j + matchingSize) % matchingSize), shuffledMemberIds.get(i));
            }
        }
        return reviewerResult;
    }

    private Map<Long, Long> matchReviewees(int matchingSize, List<Long> shuffledMemberIds) {
        Map<Long, Long> revieweeResult = new HashMap<>();
        for (int i = 0; i < shuffledMemberIds.size(); i++) {
            for (int j = 1; j < matchingSize; j++) {
                revieweeResult.put(shuffledMemberIds.get(i), shuffledMemberIds.get((i + j) % matchingSize));
            }
        }
        return revieweeResult;
    }
}
