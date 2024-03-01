# 감자마켓 - 동네기반의 유저간 거래 앱 API 서버 (당근마켓 클론)

![Coverage](.github/badges/jacoco.svg) 
![branches.svg](.github/badges/branches.svg)

# Description

동네기반의 유저간 거래를 할 수 있는 서비스입니다.


## 주요 관심사
### 공통사항
- Spring Formatter 컨벤션 적용
- MDC + Logback을 활용한 로깅 적용
- 스웨거를 통한 문서화
- 비용절감을 위한 이미지 리사이징 (Todo)
### 테스트
- TDD 기반으로 테스트 커버리지 90% 이상을 유지
- WireMock을 통한 외부 API 테스트
### 유저경험
- 분산환경에서 로그인 유저경험을 향상시키기 위해 세션 스토리지를 분리
### 성능
- 레디스 캐싱을 적용을 통한 읽기 성능 향상 (Todo)
- open-in-view 해제를 통한 트랜잭션 범위 축소
- default_batch_fetch_size를 통한 one to many N + 1 문제 해결

# Technologies

- Backend: SpringBoot, Java, JPA, JUnit
- Infra: Redis, MySQL

# Test
To See Test Coverage Document
```shell
./gradlew test
open ./build/reports/jacoco/test/html/index.html 
```