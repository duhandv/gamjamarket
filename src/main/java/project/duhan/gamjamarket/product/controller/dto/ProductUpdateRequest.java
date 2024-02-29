package project.duhan.gamjamarket.product.controller.dto;

import project.duhan.gamjamarket.common.domain.Money;
import project.duhan.gamjamarket.product.service.dto.ProductUpdateCommand;

public record ProductUpdateRequest(String name, Long amount, Long categoryId) {
    public ProductUpdateCommand toCommand(Long productId, Long memberId) {
        return new ProductUpdateCommand(productId, memberId, name, Money.wons(amount), categoryId);
    }
}
