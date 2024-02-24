package project.duhan.gamjamarket.product.controller.dto;

import project.duhan.gamjamarket.common.domain.Money;
import project.duhan.gamjamarket.product.service.dto.ProductRegisterCommand;

public record ProductRegisterRequest(String name, Long amount, Long categoryId) {
    public ProductRegisterCommand toCommand(Long memberId) {
        return new ProductRegisterCommand(memberId, name, Money.wons(amount), categoryId);
    }
}
