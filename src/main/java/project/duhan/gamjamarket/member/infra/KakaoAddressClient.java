package project.duhan.gamjamarket.member.infra;

import org.springframework.stereotype.Component;
import project.duhan.gamjamarket.member.domain.Address;
import project.duhan.gamjamarket.member.domain.AddressClient;

@Component
public class KakaoAddressClient implements AddressClient {

    private final KakaoLocalApi kakaoLocalApi;

    private final KakaoClientProperties properties;

    public KakaoAddressClient(KakaoLocalApi kakaoLocalApi, KakaoClientProperties properties) {
        this.kakaoLocalApi = kakaoLocalApi;
        this.properties = properties;
    }

    @Override
    public Address fetch(double longitude, double latitude) {
        KakaoAddressFetchResult fetchResult = kakaoLocalApi.fetchAddress(getKakaoAuthorization(), longitude, latitude);
        return fetchResult.toAddress();
    }

    private String getKakaoAuthorization() {
        return "KakaoAK " + properties.apiSecretKey();
    }

}
