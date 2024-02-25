package project.duhan.gamjamarket.product.dommain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import project.duhan.gamjamarket.common.domain.Money;

@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private String name;

    private String region;

    private Money amount;

    private Long categoryId;

    private int likeCount = 0;

    private ProductState state = ProductState.WAIT;

    protected Product() {
    }

    @Builder
    public Product(String name, Long memberId, String region, Money amount, Long categoryId) {
        this.name = name;
        this.memberId = memberId;
        this.region = region;
        this.amount = amount;
        this.categoryId = categoryId;
    }

    public void sold() {
        state = ProductState.SOLD;
    }

    public void delete() {
        if (state != ProductState.WAIT) {
            throw new IllegalStateException("대기상태 상품만 삭제할 수 있습니다.");
        }
        state = ProductState.DELETED;
    }

    public void like() {
        likeCount++;
    }

    public void update(String name, Money amount, String region, Long categoryId) {
        this.name = name;
        this.amount = amount;
        this.region = region;
        this.categoryId = categoryId;
    }

}
