package project.duhan.gamjamarket.product.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.duhan.gamjamarket.common.domain.Money;
import project.duhan.gamjamarket.product.dommain.Product;
import project.duhan.gamjamarket.product.dommain.ProductRepository;
import project.duhan.gamjamarket.support.tset.RepositoryTest;

import static org.assertj.core.api.BDDAssertions.then;

@RepositoryTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveProduct() {
        Product savedProduct = productRepository
            .save(Product.builder().name("상품1").region("대방동").categoryId(1L).amount(Money.wons(5000)).build());

        Product findProduct = productRepository.findById(savedProduct.getId()).orElseThrow();

        then(findProduct.getName()).isEqualTo("상품1");
        then(findProduct.getAmount()).isEqualTo(Money.wons(5000));
    }

}
