package project.duhan.gamjamarket.common.auth;

import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.context.request.NativeWebRequest;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

class LoginMemberArgumentResolverTest {

    private final LoginMemberArgumentResolver loginMemberArgumentResolver = new LoginMemberArgumentResolver();

    @Test
    void verifySuccess_whenHasLoginAnnotation() throws NoSuchMethodException {
        Method method = TestAuthController.class.getDeclaredMethod("user", Long.class);
        MethodParameter methodParameter = MethodParameter.forExecutable(method, 0);

        then(loginMemberArgumentResolver.supportsParameter(methodParameter)).isTrue();
    }

    @Test
    void verifyFalse_whenNotHasLoginAnnotation() throws NoSuchMethodException {
        Method method = TestAuthController.class.getDeclaredMethod("notHasLoginAnnotation", Long.class);
        MethodParameter methodParameter = MethodParameter.forExecutable(method, 0);

        then(loginMemberArgumentResolver.supportsParameter(methodParameter)).isFalse();
    }

    @Test
    void throwException_whenSessionIsNull() {
        NativeWebRequest webRequest = mock(NativeWebRequest.class);
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        given(webRequest.getNativeRequest()).willReturn(httpServletRequest);

        thenThrownBy(() -> loginMemberArgumentResolver.resolveArgument(null, null, webRequest, null))
            .isInstanceOf(UnAuthenticateException.class);
    }

    @Test
    void returnAuthorizationId_whenSessionIsNotNull() throws Exception {
        NativeWebRequest webRequest = mock(NativeWebRequest.class);
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(AUTHORIZATION, 33L);
        httpServletRequest.setSession(session);
        given(webRequest.getNativeRequest()).willReturn(httpServletRequest);

        Long authorizationId = (Long) loginMemberArgumentResolver.resolveArgument(null, null, webRequest, null);

        then(authorizationId).isEqualTo(33L);
    }

    static class TestAuthController {

        public void user(@Login Long memberId) {
        }

        public void notHasLoginAnnotation(Long memberId) {
        }

    }

}