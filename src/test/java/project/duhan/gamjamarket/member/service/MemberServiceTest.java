package project.duhan.gamjamarket.member.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.duhan.gamjamarket.member.domain.Address;
import project.duhan.gamjamarket.member.domain.Member;
import project.duhan.gamjamarket.member.domain.MemberRepository;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private AddressGetter addressGetter;

    @Test
    void updateUserRegion_whenVerifyRegion() {
        Member member = Member.builder().build();
        double latitude = 37.5519;
        double longitude = 126.9918;
        given(memberRepository.findById(1L)).willReturn(Optional.of(member));
        given(addressGetter.get(latitude, longitude)).willReturn(new Address("서울특별시", "동작구", "대방동"));

        memberService.verifyRegion(1L, latitude, longitude);

        then(member.getAddress().getDepth1()).isEqualTo("서울특별시");
        then(member.getAddress().getDepth2()).isEqualTo("동작구");
        then(member.getAddress().getDepth3()).isEqualTo("대방동");
    }

    @Test
    void updateUserRegion_whenVerifyRegion2() {
        Member member = Member.builder().build();
        double latitude = 37.5519;
        double longitude = 126.9918;
        given(memberRepository.findById(1L)).willReturn(Optional.of(member));
        given(addressGetter.get(latitude, longitude)).willReturn(new Address("서울특별시", "강남구", "청담동"));

        memberService.verifyRegion(1L, latitude, longitude);

        then(member.getAddress().getDepth1()).isEqualTo("서울특별시");
        then(member.getAddress().getDepth2()).isEqualTo("강남구");
        then(member.getAddress().getDepth3()).isEqualTo("청담동");
    }

}
