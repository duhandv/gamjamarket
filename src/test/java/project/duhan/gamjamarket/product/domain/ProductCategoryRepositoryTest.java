package project.duhan.gamjamarket.product.domain;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.duhan.gamjamarket.product.dommain.ProductCategory;
import project.duhan.gamjamarket.product.dommain.ProductCategoryRepository;
import project.duhan.gamjamarket.support.tset.RepositoryTest;

import static org.assertj.core.api.BDDAssertions.then;

@RepositoryTest
class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    void saveProductCategory() {
        List<ProductCategory> categories = List.of(new ProductCategory("가전"), new ProductCategory("생활"),
                new ProductCategory("스포츠"));
        List<String> categoryNames = categories.stream().map(ProductCategory::getName).toList();
        productCategoryRepository.saveAll(categories);

        List<ProductCategory> actual = productCategoryRepository.findAll();

        List<String> savedNames = actual.stream().map(ProductCategory::getName).toList();
        then(actual).hasSize(3);
        then(savedNames).containsAll(categoryNames);
    }

}