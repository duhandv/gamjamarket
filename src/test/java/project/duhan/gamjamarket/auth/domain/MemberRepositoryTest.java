package project.duhan.gamjamarket.auth.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.duhan.gamjamarket.member.domain.Member;
import project.duhan.gamjamarket.member.domain.MemberRepository;
import project.duhan.gamjamarket.support.tset.RepositoryTest;

import static org.assertj.core.api.BDDAssertions.then;

@RepositoryTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void save() {
        Member member = Member.builder().loginId("loginId").password("password").build();
        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId()).orElseThrow();
        then(findMember.getLoginId()).isEqualTo("loginId");
        then(findMember.getPassword()).isEqualTo("password");
    }

}