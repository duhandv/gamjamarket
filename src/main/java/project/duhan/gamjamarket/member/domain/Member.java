package project.duhan.gamjamarket.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

    private String password;

    private String phone;

    @Enumerated(value = EnumType.STRING)
    private RegionVerificationState regionVerificationState = RegionVerificationState.NONE;

    private String region;

    protected Member() {
    }

    @Builder
    public Member(Long id, String loginId, String password, String phone) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.phone = phone;
    }

    public void authenticate(String password) {
        if (!this.password.equals(password)) {
            throw new BadCredentialException();
        }
    }

    public void verifyRegion() {
        regionVerificationState = RegionVerificationState.VERIFIED;
    }

}
