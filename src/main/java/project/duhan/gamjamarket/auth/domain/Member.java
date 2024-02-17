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

    @Builder
    public Member(String loginId, String password, String phone) {
        this.loginId = loginId;
        this.password = password;
        this.phone = phone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
