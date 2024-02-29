package project.duhan.gamjamarket.product.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import project.duhan.gamjamarket.common.domain.Money;

import static org.mockito.BDDMockito.isA;
import static org.mockito.BDDMockito.verify;
import static org.mockito.Mockito.times;

@SpringBootTest
@Transactional
class LikeLifeCycleHandlerIGTest {

    @Autowired
    private ProductRepository productRepository;

    @MockBean
    private LikeLifeCycleHandler handler;

    @Test
    void handleProductLikedEvent() {
        Product product = Product.builder().name("무궁화호").amount(Money.wons(10000)).memberId(1L).build();
        product.like(2L);
        productRepository.save(product);

        verify(handler, times(1)).handle(isA(ProductLikedEvent.class));
    }

    @Test
    void handleProductCancelLikedEvent() {
        Product product = Product.builder().name("무궁화호").amount(Money.wons(10000)).memberId(1L).build();
        product.cancelLike(2L);
        productRepository.save(product);
        verify(handler, times(1)).handle(isA(ProductCancelLikedEvent.class));
    }

}