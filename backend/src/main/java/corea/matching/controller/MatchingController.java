package corea.matching.controller;

import corea.auth.annotation.LoginMember;
import corea.auth.domain.AuthInfo;
import corea.matching.domain.PullRequestInfo;
import corea.matching.service.MatchingService;
import corea.matching.service.PullRequestProvider;
import corea.room.dto.RoomResponse;
import corea.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MatchingController implements MacthingControllerSpecification {

    private final MatchingService matchingService;
    private final RoomService roomService;
    private final PullRequestProvider pullRequestProvider;

    @PostMapping("/rooms/{id}/matching")
    public ResponseEntity<Void> matching(@PathVariable long id, @LoginMember AuthInfo authInfo) {
//        RoomResponse response = roomService.getRoomById(id);
//        PullRequestInfo pullRequestInfo = pullRequestProvider.getUntilDeadline(response.repositoryLink(), response.recruitmentDeadline());
        new PullRequestInfo(Map.of());
        matchingService.match(id, new PullRequestInfo(Map.of()));
        return ResponseEntity.ok()
                .build();
    }
}
