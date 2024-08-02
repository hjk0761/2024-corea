package corea.auth.service;

import corea.auth.domain.AuthenticationAuthorizationInfo;
import corea.auth.domain.TokenGenerator;
import corea.auth.repository.AuthenticationAuthorizationInfoRepository;
import corea.exception.CoreaException;
import corea.member.domain.Member;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static corea.exception.ExceptionType.INVALID_TOKEN;
import static corea.exception.ExceptionType.TOKEN_EXPIRED;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthenticationAuthorizationService {

    @Value("${security.jwt.token.expire-length.access}")
    private long validityForAccessInMilliseconds;

    @Value("${security.jwt.token.expire-length.refresh}")
    private long validityForRefreshInMilliseconds;

    private static final String TOKEN_TYPE = "Bearer ";
    private static final String COOKIE_NAME = "cookie";

    private final AuthenticationAuthorizationInfoRepository authenticationAuthorizationInfoRepository;
    private final TokenGenerator tokenGenerator;

    public String createAccessToken(Member member) {
        return TOKEN_TYPE.concat(tokenGenerator.createToken(member, validityForAccessInMilliseconds));
    }

    @Transactional
    public Cookie createRefreshCookie(Member member) {
        String jwtToken = tokenGenerator.createToken(member, validityForRefreshInMilliseconds);
        authenticationAuthorizationInfoRepository.save(new AuthenticationAuthorizationInfo(member, jwtToken));
        Cookie cookie = new Cookie(COOKIE_NAME, jwtToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }

    @Transactional
    public void validateRefreshToken(String token) {
        try {
            tokenGenerator.validateToken(token);
        } catch (CoreaException e) {
            if (e.getExceptionType().equals(TOKEN_EXPIRED)) {
                authenticationAuthorizationInfoRepository.deleteByRefreshToken(token);
                throw new CoreaException(TOKEN_EXPIRED);
            }
            throw new CoreaException(INVALID_TOKEN);
        }

        AuthenticationAuthorizationInfo info = authenticationAuthorizationInfoRepository.findByRefreshToken(token)
                .orElseThrow(() -> new CoreaException(INVALID_TOKEN));

        Long tokenMemberId = tokenGenerator.getPayload(token).get("id", Long.class);
        if (!info.getMember().getId().equals(tokenMemberId)) {
            throw new CoreaException(INVALID_TOKEN);
        }
    }
}
