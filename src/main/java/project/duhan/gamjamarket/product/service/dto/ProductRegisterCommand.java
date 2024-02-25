package project.duhan.gamjamarket.product.service.dto;

import project.duhan.gamjamarket.common.domain.Money;
import project.duhan.gamjamarket.product.dommain.Product;

public record ProductRegisterCommand(Long memberId, String name, Money amount, Long categoryId) {
    public Product toEntity(String region) {
        return Product.builder()
            .memberId(memberId)
            .name(name)
            .amount(amount)
            .region(region)
            .categoryId(categoryId)
            .build();
    }
}
