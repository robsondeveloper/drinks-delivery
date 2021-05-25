package drinks.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(enableDefaultTransactions = false, basePackages = "drinks.domain.repository")
public class JpaConfig {

}
