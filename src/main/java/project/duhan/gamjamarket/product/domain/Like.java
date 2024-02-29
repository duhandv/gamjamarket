package project.duhan.gamjamarket.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import project.duhan.gamjamarket.common.domain.BaseEntity;

@Getter
@Entity
@Table(name = "likes")
public class Like extends BaseEntity {

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "product_id")
    private Long productId;

    protected Like() {
    }

    public Like(Long memberId, Long productId) {
        this.memberId = memberId;
        this.productId = productId;
    }

}
