package project.duhan.gamjamarket.member.service;

import org.springframework.stereotype.Component;
import project.duhan.gamjamarket.member.domain.Address;
import project.duhan.gamjamarket.member.domain.AddressClient;

@Component
public class AddressGetter {

    private final AddressClient addressClient;

    public AddressGetter(AddressClient addressClient) {
        this.addressClient = addressClient;
    }

    public Address get(double longitude, double latitude) {
        return addressClient.fetch(longitude, latitude);
    }

}
