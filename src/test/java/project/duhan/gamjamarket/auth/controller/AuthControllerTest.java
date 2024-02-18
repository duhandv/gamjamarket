package project.duhan.gamjamarket.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import project.duhan.gamjamarket.auth.application.AuthService;
import project.duhan.gamjamarket.auth.controller.dto.MemberLoginRequest;
import project.duhan.gamjamarket.auth.controller.dto.MemberRegisterRequest;
import project.duhan.gamjamarket.member.domain.BadCredentialException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @Test
    void postMemberRegister() throws Exception {
        MemberRegisterRequest request = new MemberRegisterRequest("loginId", "password", "01012341234");
        String jsonContent = objectMapper.writeValueAsString(request);

        willDoNothing().given(authService).register(any(), any(), any());

        mockMvc.perform(post("/api/auth/register").content(jsonContent).contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void postMemberLoginSuccess() throws Exception {
        MemberLoginRequest request = new MemberLoginRequest("loginId", "password");
        String jsonContent = objectMapper.writeValueAsString(request);

        given(authService.login("loginId", "password")).willReturn(2L);

        mockMvc.perform(post("/api/auth/login").content(jsonContent).contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void postMemberLoginFail() throws Exception {
        MemberLoginRequest request = new MemberLoginRequest("loginId", "password");
        String jsonContent = objectMapper.writeValueAsString(request);

        given(authService.login("loginId", "password")).willThrow(new BadCredentialException());

        mockMvc.perform(post("/api/auth/login").content(jsonContent).contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isForbidden());
    }

    @Test
    void postMemberLogout() throws Exception {
        mockMvc.perform(post("/api/auth/logout")).andDo(print()).andExpect(status().isOk());
    }

}