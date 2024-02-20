package project.duhan.gamjamarket.member.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class MemberTest {

    @Test
    void memberRegionVerification_isNotVerified_whenCreated() {
        Member member = Member.builder().build();

        then(member.getRegionVerificationState()).isEqualTo(RegionVerificationState.NONE);
    }

    @Test
    void memberRegionVerification_isVerified_whenVerified() {
        Member member = Member.builder().build();
        member.verifyRegion();

        then(member.getRegionVerificationState()).isEqualTo(RegionVerificationState.VERIFIED);
    }

}