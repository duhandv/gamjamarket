package project.duhan.gamjamarket.product.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import project.duhan.gamjamarket.product.domain.Product;

import java.util.List;

import static project.duhan.gamjamarket.product.domain.QProduct.product;

@Repository
public class ProductQueryDao {

    private final JPAQueryFactory queryFactory;

    public ProductQueryDao(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<Product> findAllByRegion(String region) {
        return queryFactory.select(product).from(product).where(product.region.eq(region)).fetch();
    }

}
