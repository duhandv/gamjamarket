package project.duhan.gamjamarket.auth.application;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.duhan.gamjamarket.member.domain.BadCredentialException;
import project.duhan.gamjamarket.member.domain.Member;
import project.duhan.gamjamarket.member.domain.MemberRepository;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    void register() {
        authService.register("loginId", "password", "01012341234");

        verify(memberRepository, times(1)).save(any());
    }

    @Test
    void failLoginWhenLoginIdNotMatched() {
        given(memberRepository.findByLoginId("invalidLoginId")).willReturn(Optional.empty());

        assertThrows(BadCredentialException.class, () -> authService.login("invalidLoginId", "password"));
    }

    @Test
    void failLoginWhenPasswordNotMatched() {
        Member member = Member.builder().id(1L).loginId("loginId").password("password").build();
        given(memberRepository.findByLoginId("loginId")).willReturn(Optional.of(member));

        assertThrows(BadCredentialException.class, () -> authService.login("loginId", "invalidPassword"));
    }

    @Test
    void successLoginWhenLoginIdAndPasswordMatched() {
        Member member = Member.builder().id(1L).loginId("loginId").password("password").build();
        given(memberRepository.findByLoginId("loginId")).willReturn(Optional.of(member));

        Long actual = authService.login("loginId", "password");

        then(actual).isEqualTo(1L);
    }

}