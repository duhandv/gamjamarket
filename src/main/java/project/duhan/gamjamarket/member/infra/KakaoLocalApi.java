package project.duhan.gamjamarket.member.infra;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@FeignClient(name = "kakao-map", url = "${kakao.map.url}")
public interface KakaoLocalApi {

    @GetMapping(value = "/v2/local/geo/coord2regioncode")
    KakaoAddressFetchResult fetchAddress(@RequestHeader(AUTHORIZATION) String apiSecretKey,
            @RequestParam(value = "x") double longitude, @RequestParam(value = "y") double latitude);

}
