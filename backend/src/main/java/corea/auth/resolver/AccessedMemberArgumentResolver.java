package corea.auth.resolver;

import corea.auth.RequestHandler;
import corea.auth.annotation.AccessedMember;
import corea.auth.domain.AuthInfo;
import corea.auth.domain.TokenGenerator;
import corea.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class AccessedMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final RequestHandler requestHandler;
    private final MemberRepository memberRepository;
    private final TokenGenerator tokenGenerator;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AccessedMember.class);
    }

    @Override
    public AuthInfo resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                    NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        String accessToken = requestHandler.extract(request);

        if (accessToken.equals("ANONYMOUS")) {
            return AuthInfo.getAnonymous();
        }
        Long memberId = tokenGenerator.getPayload(accessToken).get("id", Long.class);

        return memberRepository.findById(memberId)
                .map(AuthInfo::from)
                .orElse(AuthInfo.getAnonymous());
    }
}
