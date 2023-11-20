package emisar.soft.ioc.container;

import emisar.soft.ioc.container.config.ApplicationConfiguration;
import emisar.soft.ioc.container.entity.Animal;
import emisar.soft.ioc.container.entity.Cat;
import emisar.soft.ioc.container.entity.Dog;
import emisar.soft.ioc.container.entity.Zoo;
import emisar.soft.ioc.container.exception.bean.NoUniqueBeanDefinitionException;
import emisar.soft.ioc.container.factory.BeanFactory;
import emisar.soft.ioc.container.factory.impl.JavaBeanFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BeanFactoryTest {

    private static BeanFactory beanFactory;

    @BeforeAll
    public static void initialization() throws ReflectiveOperationException {
        beanFactory = JavaBeanFactory.create(ApplicationConfiguration.class);
    }

    @Test
    void getZoo() {
        Zoo zoo = beanFactory.getBean(Zoo.class);
        Assertions.assertEquals(Cat.class, zoo.getCat().getClass());
        Assertions.assertEquals(Dog.class, zoo.getDog().getClass());
    }

    @Test
    void getAnimalsByTheyClass() {
        Cat cat = beanFactory.getBean(Cat.class);
        Dog dog = beanFactory.getBean(Dog.class);
        Assertions.assertEquals(9, cat.getLives());
        Assertions.assertEquals(99, dog.getHappyLevel());
    }

    @Test
    void getAnimalsByTheyName() {
        Object cat = beanFactory.getBean("cat");
        Object dog = beanFactory.getBean("dog");
        Assertions.assertEquals(Cat.class, cat.getClass());
        Assertions.assertEquals(Dog.class, dog.getClass());
    }

    @Test
    void getAnimalByAncestor() {
        Cat cat = beanFactory.getBean(Cat.class);
        Animal animal = beanFactory.getBean(Animal.class);
        Assertions.assertEquals(cat.getClass(), animal.getClass());
    }

    @Test
    void getAnimalsByAncestorNoUniqueException() {
        Cat cat = beanFactory.getBean(Cat.class);
        Dog dog = beanFactory.getBean(Dog.class);
        Assertions.assertThrowsExactly(NoUniqueBeanDefinitionException.class, () -> {
            Animal animal = beanFactory.getBean(Animal.class);
        });
    }

    @Test
    void getSingletonBean() {
        Cat cat1 = beanFactory.getBean(Cat.class);
        Object cat2 = beanFactory.getBean("cat");
        Assertions.assertEquals(cat1, cat2);
    }

}