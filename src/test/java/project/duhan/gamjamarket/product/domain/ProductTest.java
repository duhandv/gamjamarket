package project.duhan.gamjamarket.product.domain;

import org.junit.jupiter.api.Test;
import project.duhan.gamjamarket.common.domain.Money;
import project.duhan.gamjamarket.product.dommain.Product;
import project.duhan.gamjamarket.product.dommain.ProductState;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

public class ProductTest {

    @Test
    void productStateIsWait_whenCreated() {
        Product product = Product.builder().build();
        then(product.getState()).isEqualTo(ProductState.WAIT);
    }

    @Test
    void productStateIsSold_whenSold() {
        Product product = Product.builder().build();
        product.sold();
        then(product.getState()).isEqualTo(ProductState.SOLD);
    }

    @Test
    void productStateIsDeleted_whenDelete() {
        Product product = Product.builder().build();
        product.delete();
        then(product.getState()).isEqualTo(ProductState.DELETED);
    }

    @Test
    void productLikeCountIncrease() {
        Product product = Product.builder().build();
        product.like();
        product.like();
        then(product.getLikeCount()).isEqualTo(2);
    }

    @Test
    void throwException_whenDelete_thatSoldProduct() {
        Product product = Product.builder().build();
        product.sold();
        thenThrownBy(product::delete).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void productUpdate() {
        Product product = Product.builder()
            .name("28인치 모니터")
            .memberId(99L)
            .amount(Money.wons(130000))
            .region("대방동")
            .categoryId(1L)
            .build();

        product.update("28인치 FHD 모니터", Money.wons(125000), "청담동", 2L);

        then(product.getMemberId()).isEqualTo(99L);
        then(product.getName()).isEqualTo("28인치 FHD 모니터");
        then(product.getAmount()).isEqualTo(Money.wons(125000));
        then(product.getRegion()).isEqualTo("청담동");
        then(product.getCategoryId()).isEqualTo(2L);
    }

    @Test
    void productBuilderToString() {
        String string = Product.builder().toString();
        then(string).contains("Product");
    }

}
