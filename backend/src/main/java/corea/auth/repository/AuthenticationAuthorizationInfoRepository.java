package corea.auth.repository;

import corea.auth.domain.AuthenticationAuthorizationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthenticationAuthorizationInfoRepository extends JpaRepository<AuthenticationAuthorizationInfo, Long> {

    Optional<AuthenticationAuthorizationInfo> findByRefreshToken(String refreshToken);

    void deleteByRefreshToken(String refreshToken);
}
