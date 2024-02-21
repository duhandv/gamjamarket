package project.duhan.gamjamarket.member.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.duhan.gamjamarket.member.domain.Member;
import project.duhan.gamjamarket.member.domain.MemberRepository;
import project.duhan.gamjamarket.member.service.dto.MemberQueryResponse;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
class MemberQueryServiceTest {

    @InjectMocks
    private MemberQueryService memberQueryService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    void returnMemberInfo_whenGetMemberInfo() {
        Member member = Member.builder().phone("01012341234").build();
        BDDMockito.given(memberRepository.findById(1L)).willReturn(Optional.of(member));

        MemberQueryResponse actual = memberQueryService.get(1L);

        then(actual.phone()).isEqualTo("01012341234");
    }

}