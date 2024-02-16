package project.duhan.gamjamarket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GamjamarketApplication {

    public static final Logger logger = LoggerFactory.getLogger(GamjamarketApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GamjamarketApplication.class, args);
        logger.debug("debug message");
        logger.info("info message");
        logger.warn("warn message");
        logger.error("error message");
    }

}
