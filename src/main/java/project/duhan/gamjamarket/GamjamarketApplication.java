package project.duhan.gamjamarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@ConfigurationPropertiesScan
@EnableFeignClients
@SpringBootApplication
public class GamjamarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamjamarketApplication.class, args);
    }

}
