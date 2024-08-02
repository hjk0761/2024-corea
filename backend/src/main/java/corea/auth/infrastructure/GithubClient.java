package corea.auth.infrastructure;

import corea.auth.domain.GithubUserInfo;
import corea.exception.CoreaException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static corea.exception.ExceptionType.GITHUB_AUTHORIZATION_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class GithubClient {

    @Value("${oauth.github.client_id}")
    private String clientId;

    @Value("${oauth.github.client_secret}")
    private String clientSecret;

    @Qualifier("auth")
    private RestClient authClient;

    @Qualifier("user")
    private RestClient userClient;

    public String getAccessToken(String code) {
        String result = getAccess(code);
        validate(result);
        return result;
    }

    private String getAccess(String code) {
        return authClient.post()
                .uri(uriBuilder -> uriBuilder.path("/")
                        .queryParam("client_id", clientId)
                        .queryParam("client_secret", clientSecret)
                        .queryParam("code", code)
                        .build())
                .contentType(APPLICATION_JSON)
                .retrieve()
                .body(String.class);
    }

    private void validate(String result) {
        if (result.contains("\"error\"")) {
            throw new CoreaException(GITHUB_AUTHORIZATION_ERROR);
        }
    }

    public GithubUserInfo getUserInfo(String accessToken) {
        return userClient.get()
                .accept(APPLICATION_JSON)
                .header("Authorization", accessToken)
                .retrieve()
                .toEntity(GithubUserInfo.class)
                .getBody();
    }
}
