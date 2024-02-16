package project.duhan.gamjamarket.auth.domain;

import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class Member {

    private Long id;

    private String loginId;

    private String password;

    private String phone;

    private LocalDateTime createdAt;

    protected Member() {

    }

    public Member(Long id, String loginId, String password, String phone, LocalDateTime createdAt) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
