package project.duhan.gamjamarket.product.controller;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import project.duhan.gamjamarket.product.controller.dto.ProductRegisterRequest;
import project.duhan.gamjamarket.product.controller.dto.ProductUpdateRequest;
import project.duhan.gamjamarket.product.service.ProductQueryService;
import project.duhan.gamjamarket.product.service.ProductService;
import project.duhan.gamjamarket.product.service.dto.ProductQueryResponse;
import project.duhan.gamjamarket.product.service.dto.ProductUpdateCommand;
import project.duhan.gamjamarket.support.tset.RestControllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest extends RestControllerTest {

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductQueryService productQueryService;

    @Test
    void postProductRegister() throws Exception {
        ProductRegisterRequest request = new ProductRegisterRequest("키보드", 10000L, 2L);
        String jsonContent = objectMapper.writeValueAsString(request);
        willDoNothing().given(productService).register(any());

        mockMvc
            .perform(
                    post("/api/products").session(login()).content(jsonContent).contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void postProductUpdate() throws Exception {
        ProductUpdateRequest request = new ProductUpdateRequest("모니터", 10000L, 2L);
        String jsonContent = objectMapper.writeValueAsString(request);
        willDoNothing().given(productService).update(isA(ProductUpdateCommand.class));

        mockMvc
            .perform(post("/api/products/{productId}", 1L).session(login())
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void getProductFindAll() throws Exception {
        List<ProductQueryResponse> response = List.of(new ProductQueryResponse(1L, "모니터", 1000L, 1L),
                new ProductQueryResponse(1L, "키보드", 700L, 1L), new ProductQueryResponse(1L, "마우스", 500L, 1L));
        given(productQueryService.findAll(any())).willReturn(response);

        mockMvc.perform(get("/api/products").session(login()))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(dataResult(response));
    }

    @Test
    void postProductLike() throws Exception {
        willDoNothing().given(productService).like(any(), any());

        mockMvc.perform(post("/api/products/{productId}/like", 1L).session(login()))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void postProductCancelLike() throws Exception {
        willDoNothing().given(productService).cancelLike(any(), any());

        mockMvc.perform(post("/api/products/{productId}/cancel-like", 1L).session(login()))
            .andDo(print())
            .andExpect(status().isOk());
    }

    private MockHttpSession login() {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(AUTHORIZATION, 1L);
        return session;
    }

}
