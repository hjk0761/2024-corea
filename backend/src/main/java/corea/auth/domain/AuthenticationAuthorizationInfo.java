package corea.auth.domain;

import corea.member.domain.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Getter
//TODO: AuthInfo 는 이 엔티티에 더 잘 어울리는 것 같습니다. 나중에 바꿔치기 할 겁니다. 건들지마셈.
public class AuthenticationAuthorizationInfo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    private String refreshToken;

    public AuthenticationAuthorizationInfo(Member member, String refreshToken) {
        this(null, member, refreshToken);
    }
}
