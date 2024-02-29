package project.duhan.gamjamarket.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import project.duhan.gamjamarket.common.domain.Money;
import project.duhan.gamjamarket.common.domain.MoneyConverter;

@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "name")
    private String name;

    @Column(name = "region")
    private String region;

    @Convert(converter = MoneyConverter.class)
    @Column(name = "amount")
    private Money amount;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "like_count")
    private int likeCount = 0;

    private ProductState state = ProductState.WAIT;

    protected Product() {
    }

    @Builder
    public Product(Long id, String name, Long memberId, String region, Money amount, Long categoryId) {
        this.id = id;
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
