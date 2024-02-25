package project.duhan.gamjamarket.product.dommain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import project.duhan.gamjamarket.common.domain.BaseEntity;

@Getter
@Entity
public class ProductCategory extends BaseEntity {

    @Column(name = "name")
    private String name;

    protected ProductCategory() {
    }

    public ProductCategory(String name) {
        this.name = name;
    }

}
