package project.duhan.gamjamarket.member.service.dto;

import project.duhan.gamjamarket.member.domain.Member;

public record MemberQueryResponse(String phone, String region) {

    public MemberQueryResponse(Member member) {
        this(member.getPhone(), member.getRegion());
    }

}
