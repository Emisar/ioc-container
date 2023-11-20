package emisar.soft.ioc.container.config;

import emisar.soft.ioc.container.annotation.Bean;
import emisar.soft.ioc.container.annotation.Configuration;
import emisar.soft.ioc.container.entity.Cat;

@Configuration
public class BeanFieldConfiguration {

    @Bean
    public Cat cat;
}
