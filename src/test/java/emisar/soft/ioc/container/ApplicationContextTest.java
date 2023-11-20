package emisar.soft.ioc.container;

import emisar.soft.ioc.container.config.ApplicationConfiguration;
import emisar.soft.ioc.container.context.ApplicationContext;
import emisar.soft.ioc.container.context.impl.JavaApplicationContext;
import emisar.soft.ioc.container.entity.Cat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ApplicationContextTest {

    private static ApplicationContext applicationContext;

    @BeforeAll
    public static void initialization() throws ReflectiveOperationException {
        applicationContext = JavaApplicationContext.create(ApplicationConfiguration.class);
    }

    @Test
    void isBeansInitialized() {
        boolean isContain = applicationContext.containsBean("dog");
        Assertions.assertTrue(isContain);
    }

    @Test
    void isBeansPostConstruct() {
        Cat cat = applicationContext.getBean(Cat.class);
        Assertions.assertEquals("Meow-meow", cat.getSayLine());
    }
}
