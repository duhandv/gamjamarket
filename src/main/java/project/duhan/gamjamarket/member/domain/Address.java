package project.duhan.gamjamarket.member.domain;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class Address {

    @Column(name = "address_depth1")
    private String depth1;

    @Column(name = "address_depth2")
    private String depth2;

    @Column(name = "address_depth3")
    private String depth3;

    public Address(String depth1, String depth2, String depth3) {
        this.depth1 = depth1;
        this.depth2 = depth2;
        this.depth3 = depth3;
    }

}
