package project.duhan.gamjamarket.auth.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.duhan.gamjamarket.auth.domain.BadCredentialException;
import project.duhan.gamjamarket.auth.domain.Member;
import project.duhan.gamjamarket.auth.domain.MemberRepository;

@Service
@Transactional
public class AuthService {

    private final MemberRepository memberRepository;

    public AuthService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void register(String loginId, String password, String phone) {
        Member member = new Member(loginId, password, phone);
        memberRepository.save(member);
    }

    public Long login(String loginId, String password) {
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(BadCredentialException::new);
        member.authenticate(password);
        return member.getId();
    }

}
