# 감자마켓 - 동네기반의 유저간 거래 앱 API 서버 (당근마켓 클론)

![Coverage](.github/badges/jacoco.svg) 
![branches.svg](.github/badges/branches.svg)

# Description

동네기반의 유저간 거래를 할 수 있는 서비스입니다.


## 신경쓴 점

- TDD 기반으로 테스트 커버리지 90% 이상을 유지
- 분산환경에서 로그인 유저경험을 향상시키기 위해 세션 스토리지를 분리
- 실제 운영환경이라는 전제하에 MDC + Logback을 활용한 로깅 적용, 스웨거를 통한 문서화
- 레디스 캐싱을 적용을 통한 읽기 성능 향상 (Todo)
- 비용절감을 위한 이미지 리사이징 (Todo)

# Technologies

- Backend: SpringBoot, Java, JPA, JUnit
- Infra: Redis, MySQL

# Test
To See Test Coverage Document
```shell
./gradlew test
open ./build/reports/jacoco/test/html/index.html 
```