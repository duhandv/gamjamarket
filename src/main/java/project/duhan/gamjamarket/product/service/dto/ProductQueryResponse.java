package project.duhan.gamjamarket.product.service.dto;

import project.duhan.gamjamarket.product.domain.Product;

public record ProductQueryResponse(Long memberId, String name, Long amount, Long categoryId) {
    public ProductQueryResponse(Product product) {
        this(product.getMemberId(), product.getName(), product.getAmount().getAmount().longValue(),
                product.getCategoryId());
    }
}
