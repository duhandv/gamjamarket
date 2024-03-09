package project.duhan.gamjamarket.member.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import project.duhan.gamjamarket.member.service.MemberQueryService;
import project.duhan.gamjamarket.member.service.dto.MemberQueryResponse;
import project.duhan.gamjamarket.support.tset.RestControllerTest;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
class MemberControllerTest extends RestControllerTest {

    @MockBean
    private MemberQueryService memberQueryService;

    @Test
    void getMemberSuccess() throws Exception {
        MockHttpSession loginMember = login();
        MemberQueryResponse response = new MemberQueryResponse("01012341234", "서울특별시 동작구 대방동");
        given(memberQueryService.get(1L)).willReturn(response);

        mockMvc.perform(get("/api/members").session(loginMember))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(dataResult(response));
    }

    @Test
    void getMemberFail() throws Exception {
        MemberQueryResponse response = new MemberQueryResponse("01012341234", "서울특별시 동작구 대방동");
        given(memberQueryService.get(1L)).willReturn(response);

        mockMvc.perform(get("/api/members")).andDo(print()).andExpect(status().isUnauthorized());
    }

    private MockHttpSession login() {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(AUTHORIZATION, 1L);
        return session;
    }

}