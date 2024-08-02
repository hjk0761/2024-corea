package corea.auth.controller;

import corea.auth.domain.GithubUserInfo;
import corea.auth.domain.TokenGenerator;
import corea.auth.infrastructure.GithubClient;
import corea.auth.service.AuthenticationAuthorizationService;
import corea.member.domain.Member;
import corea.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LoginController implements LoginControllerSpecification {

    private final GithubClient githubClient;
    private final AuthenticationAuthorizationService authService;
    private final MemberService memberService;
    private final TokenGenerator tokenGenerator;

    @PostMapping("/login")
    public ResponseEntity<Void> login(HttpServletRequest request) {
        String code = request.getParameter("code");
        String accessToken = githubClient.getAccessToken(code);
        GithubUserInfo userInfo = githubClient.getUserInfo(accessToken);
        Member member = memberService.login(userInfo);
        String token = authService.createAccessToken(member);
        Cookie cookie = authService.createRefreshCookie(member);
        return ResponseEntity.ok()
                .header("Authorization", token)
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<Void> extendAuthorization(HttpServletRequest request) {
        String refreshToken = request.getParameter("token");
        authService.validateRefreshToken(refreshToken);
        Long memberId = tokenGenerator.getPayload(refreshToken).get("id", Long.class);
        Member member = memberService.findById(memberId);
        String token = authService.createAccessToken(member);
        return ResponseEntity.ok()
                .header("Authorization", token)
                .build();
    }
}
