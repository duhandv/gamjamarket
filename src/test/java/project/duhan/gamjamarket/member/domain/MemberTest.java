package project.duhan.gamjamarket.member.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class MemberTest {

    @Test
    void memberRegionVerification_isNotVerified_whenCreated() {
        Member member = Member.builder().build();

        then(member.getRegionVerifyState()).isEqualTo(RegionVerifyState.NONE);
    }

    @Test
    void memberRegionVerification_isVerified_whenVerified() {
        Member member = Member.builder().build();
        Address address = new Address("서울특별시", "동작구", "대방동");
        member.verifyRegion(address);

        then(member.getRegionVerifyState()).isEqualTo(RegionVerifyState.VERIFIED);
    }

    @Test
    void memberAddressCreated_whenRegionVerified() {
        Member member = Member.builder().build();
        Address address = new Address("서울특별시", "동작구", "대방동");
        member.verifyRegion(address);

        then(member.getAddress().getDepth1()).isEqualTo("서울특별시");
    }

}