package corea.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class RequestHandler {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    public String extract(HttpServletRequest request) {
        if (request.getHeader(AUTHORIZATION_HEADER) != null && request.getHeader(AUTHORIZATION_HEADER).startsWith("Bearer ")) {
            return request.getHeader(AUTHORIZATION_HEADER).replace("Bearer ", "");
        }
        return "ANONYMOUS";
    }

    public String extractToken(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION_HEADER).replace("Bearer ", "");
    }
}
