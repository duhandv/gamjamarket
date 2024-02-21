package project.duhan.gamjamarket.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import project.duhan.gamjamarket.common.DataResult;
import project.duhan.gamjamarket.member.service.MemberQueryService;
import project.duhan.gamjamarket.member.service.dto.MemberQueryResponse;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@ExtendWith(MockitoExtension.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberQueryService memberQueryService;

    @Test
    void getMember() throws Exception {
        MockHttpSession loginMember = login();
        MemberQueryResponse response = new MemberQueryResponse("01012341234", "서울특별시 동작구 대방동");

        given(memberQueryService.get(1L)).willReturn(response);

        mockMvc.perform(get("/api/member").session(loginMember))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(DataResult.of(response))));
    }

    private MockHttpSession login() {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(AUTHORIZATION, 1L);
        return session;
    }

}