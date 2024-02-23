package project.duhan.gamjamarket.member.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.duhan.gamjamarket.member.domain.Address;
import project.duhan.gamjamarket.member.domain.AddressClient;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AddressGetterTest {

    @InjectMocks
    private AddressGetter addressGetter;

    @Mock
    private AddressClient addressClient;

    @Test
    void convertAddress_stringToObject() {
        double longitude = 127.071367614735;
        double latitude = 37.5464762611364;
        given(addressClient.fetch(longitude, latitude)).willReturn(new Address("서울특별시", "동작구", "대방동"));

        Address actual = addressGetter.get(longitude, latitude);

        then(actual.getDepth1()).isEqualTo("서울특별시");
        then(actual.getDepth2()).isEqualTo("동작구");
        then(actual.getDepth3()).isEqualTo("대방동");
    }

}