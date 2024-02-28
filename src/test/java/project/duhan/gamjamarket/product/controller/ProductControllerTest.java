package project.duhan.gamjamarket.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import project.duhan.gamjamarket.product.controller.dto.ProductRegisterRequest;
import project.duhan.gamjamarket.product.controller.dto.ProductUpdateRequest;
import project.duhan.gamjamarket.product.service.ProductService;
import project.duhan.gamjamarket.product.service.dto.ProductUpdateCommand;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @Test
    void postProductRegister() throws Exception {
        MockHttpSession loginMember = login();
        ProductRegisterRequest request = new ProductRegisterRequest("키보드", 10000L, 2L);
        String jsonContent = objectMapper.writeValueAsString(request);
        willDoNothing().given(productService).register(any());

        mockMvc.perform(
                        post("/api/product").session(loginMember).content(jsonContent).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void postProductUpdate() throws Exception {
        MockHttpSession loginMember = login();
        ProductUpdateRequest request = new ProductUpdateRequest("모니터", 10000L, 2L);
        String jsonContent = objectMapper.writeValueAsString(request);
        willDoNothing().given(productService).update(isA(ProductUpdateCommand.class));

        mockMvc
                .perform(post("/api/product/{productId}", 1L).session(loginMember)
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private MockHttpSession login() {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(AUTHORIZATION, 1L);
        return session;
    }

}
