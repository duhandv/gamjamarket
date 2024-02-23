package project.duhan.gamjamarket.member.infra;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@AutoConfigureWireMock(port = 0)
@TestPropertySource(properties = "kakao.map.url=http://localhost:${wiremock.server.port}")
class KakaoLocalApiTest {

    @Autowired
    private KakaoLocalApi kakaoLocalApi;

    @Test
    void parsingKakaoMapJsonResponse() {
        stubFor(get(urlPathEqualTo("/v2/local/geo/coord2regioncode"))
            .willReturn(aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .withBodyFile("kakao-map-response.json")));

        KakaoAddressFetchResult fetchResult = kakaoLocalApi.fetchAddress("apikey", 126.926343500008, 37.508164261);

        then(fetchResult.getDocuments().getFirst().getAddressName()).isEqualTo("서울특별시 동작구 대방동");
        then(fetchResult.getDocuments().getFirst().getRegion1depthName()).isEqualTo("서울특별시");
        then(fetchResult.getDocuments().getFirst().getRegion2depthName()).isEqualTo("동작구");
        then(fetchResult.getDocuments().getFirst().getRegion3depthName()).isEqualTo("대방동");
    }

}