package project.duhan.gamjamarket;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(servers = @Server(url = "/", description = "Default Server Url"),
        info = @Info(title = "Gamja Merket", description = "동네기반 유저간 상품거래 서비스"))
@ConfigurationPropertiesScan
@EnableFeignClients
@SpringBootApplication
public class GamjamarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamjamarketApplication.class, args);
    }

}
