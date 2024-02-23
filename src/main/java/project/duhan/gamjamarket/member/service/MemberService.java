package project.duhan.gamjamarket.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.duhan.gamjamarket.member.domain.Address;
import project.duhan.gamjamarket.member.domain.Member;
import project.duhan.gamjamarket.member.domain.MemberRepository;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    private final AddressGetter addressGetter;

    public MemberService(MemberRepository memberRepository, AddressGetter addressGetter) {
        this.memberRepository = memberRepository;
        this.addressGetter = addressGetter;
    }

    public void verifyRegion(Long memberId, double longitude, double latitude) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Address address = addressGetter.get(longitude, latitude);
        member.verifyRegion(address);
    }

}
