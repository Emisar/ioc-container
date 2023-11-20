package emisar.soft.ioc.container.config;

import emisar.soft.ioc.container.annotation.Bean;
import emisar.soft.ioc.container.annotation.Configuration;
import emisar.soft.ioc.container.entity.Cat;
import emisar.soft.ioc.container.entity.Dog;
import emisar.soft.ioc.container.entity.Zoo;

@Configuration
public class BeanMethodConfiguration {

    @Bean
    public Dog dog() {
        return new Dog(99);
    }

    @Bean
    public Zoo zoo(Cat cat, Dog dog) {
        return new Zoo(cat, dog);
    }
}
