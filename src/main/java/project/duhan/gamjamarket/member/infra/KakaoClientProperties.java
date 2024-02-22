package project.duhan.gamjamarket.member.infra;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kakao")
public record KakaoClientProperties(String apiSecretKey) {
}
