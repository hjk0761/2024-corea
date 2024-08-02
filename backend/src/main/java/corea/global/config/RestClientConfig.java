package corea.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${github.baseUrl.oauth}")
    private String githubOAuth2BaseUrl;

    @Value("${github.baseUrl.user}")
    private String githubUserBaseUrl;

    @Bean(name = "auth")
    RestClient authClient() {
        return RestClient.builder()
                .baseUrl(githubOAuth2BaseUrl).build();
    }

    @Bean(name = "user")
    RestClient userClient() {
        return RestClient.builder()
                .baseUrl(githubUserBaseUrl).build();
    }
}
