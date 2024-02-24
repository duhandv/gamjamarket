package project.duhan.gamjamarket.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "region_verify_state")
    @Enumerated(value = EnumType.STRING)
    private RegionVerifyState regionVerifyState = RegionVerifyState.NONE;

    @Embedded
    private Address address;

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

    public void verifyRegion(Address address) {
        this.address = address;
        regionVerifyState = RegionVerifyState.VERIFIED;
    }

    public String getRegion() {
        return address.getDepth2();
    }

}
