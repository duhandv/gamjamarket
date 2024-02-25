package project.duhan.gamjamarket.support.tset;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import project.duhan.gamjamarket.common.config.JpaConfig;

@Import(JpaConfig.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@DataJpaTest
public @interface RepositoryTest {

}
