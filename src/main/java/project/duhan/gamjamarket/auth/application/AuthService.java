package project.duhan.gamjamarket.auth.application;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.duhan.gamjamarket.member.domain.BadCredentialException;
import project.duhan.gamjamarket.member.domain.Member;
import project.duhan.gamjamarket.member.domain.MemberRepository;

@Service
@Transactional
public class AuthService {

    private final MemberRepository memberRepository;

    public AuthService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void register(String loginId, String password, String phone) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if (findMember.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        Member member = Member.builder().loginId(loginId).password(password).phone(phone).build();
        memberRepository.save(member);
    }

    public Long login(String loginId, String password) {
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(BadCredentialException::new);
        member.authenticate(password);
        return member.getId();
    }

}
