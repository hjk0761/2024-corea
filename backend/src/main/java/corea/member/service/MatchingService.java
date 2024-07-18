package corea.member.service;

import corea.matching.domain.MatchResult;
import corea.matching.domain.Participation;
import corea.matching.domain.PlainRandomMatching;
import corea.matching.repository.MatchResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MatchingService {

    private final PlainRandomMatching plainRandomMatching;
    private final MatchResultRepository matchResultRepository;

    public void matchMaking(List<Participation> participations, int matchingSize) {
        List<Long> memberIds = participations.stream()
                .map(Participation::getMemberId)
                .toList();

        Map<Long, Long> results = plainRandomMatching.matchPairs(memberIds, matchingSize);

        results.entrySet()
                .stream()
                .map(entry -> new MatchResult(entry.getKey(), entry.getValue(), ))
                .forEach(matchResultRepository::save);
    }
}
