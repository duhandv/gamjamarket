package project.duhan.gamjamarket.auth.domain;

import jakarta.persistence.Entity;
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

    protected Member() {
    }

    public Member(String loginId, String password, String phone) {
        this(null, loginId, password, phone);
    }

    @Builder
    private Member(Long id, String loginId, String password, String phone) {
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

}
