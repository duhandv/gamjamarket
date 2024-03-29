package project.duhan.gamjamarket.common.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.duhan.gamjamarket.product.domain.ProductCategory;
import project.duhan.gamjamarket.product.domain.ProductCategoryRepository;
import project.duhan.gamjamarket.support.tset.RepositoryTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RepositoryTest
class BaseEntityRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    void getBaseEntityFields() {
        ProductCategory productCategory = new ProductCategory("가전");
        BaseEntity savedBaseEntity = productCategoryRepository.save(productCategory);

        BaseEntity findBaseEntity = productCategoryRepository.findById(savedBaseEntity.getId()).orElseThrow();

        assertNotNull(findBaseEntity.getId());
        assertThat(findBaseEntity.getCreatedAt()).isBeforeOrEqualTo(LocalDateTime.now());
        assertThat(findBaseEntity.getUpdatedAt()).isBeforeOrEqualTo(LocalDateTime.now());
    }

}