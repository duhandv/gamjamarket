package project.duhan.gamjamarket.member.infra;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;
import project.duhan.gamjamarket.member.domain.Address;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.BDDMockito.given;
import static project.duhan.gamjamarket.member.infra.KakaoAddressFetchResult.Document;
import static project.duhan.gamjamarket.member.infra.KakaoAddressFetchResult.RegionType;

@ExtendWith(MockitoExtension.class)
@TestPropertySource("classpath:application.yml")
class KakaoAddressClientTest {

    @InjectMocks
    private KakaoAddressClient kakaoAddressClient;

    @Mock
    private KakaoLocalApi kakaoLocalApi;

    @Mock
    private KakaoClientProperties properties;

    @Test
    void fetchUserAddress() {
        KakaoAddressFetchResult kakaoAddressFetchResult = createKakaoAddressFetchResult();
        given(properties.apiSecretKey()).willReturn("apiKey");
        given(kakaoLocalApi.fetchAddress(any(), anyDouble(), anyDouble())).willReturn(kakaoAddressFetchResult);

        Address actual = kakaoAddressClient.fetch(127.23333, 37.123123);

        then(actual.getDepth1()).isEqualTo("서울특별시");
        then(actual.getDepth2()).isEqualTo("동작구");
        then(actual.getDepth3()).isEqualTo("대방동");
    }

    private KakaoAddressFetchResult createKakaoAddressFetchResult() {
        return new KakaoAddressFetchResult(
                List.of(new Document(RegionType.HAENGJEONGDONG.getCode(), "서울특별시 동작구 대방동", "서울특별시", "동작구", "대방동"),
                        new Document(RegionType.BEOBJEONGDONG.getCode(), "서울특별시 동작구 대방동", "서울특별시", "동작구", "대방동")));
    }

}