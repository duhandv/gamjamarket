package project.duhan.gamjamarket.product.service.dto;

import project.duhan.gamjamarket.common.domain.Money;

public record ProductUpdateCommand(Long productId, Long memberId, String name, Money amount, Long categoryId) {
}
