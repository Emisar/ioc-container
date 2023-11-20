package emisar.soft.ioc.container.factory.impl;

import emisar.soft.ioc.container.configuration.ext.JavaBeanConfiguration;
import emisar.soft.ioc.container.container.BeanContainer;
import emisar.soft.ioc.container.container.FactoryBeanContainer;
import emisar.soft.ioc.container.container.impl.SingletonBeanContainer;
import emisar.soft.ioc.container.container.impl.SingletonFactoryBeanContainer;
import emisar.soft.ioc.container.factory.BeanFactory;
import emisar.soft.ioc.container.factory.def.DefaultBeanFactory;
import lombok.Getter;

@Getter
public class JavaBeanFactory implements DefaultBeanFactory, JavaBeanConfiguration {

    private final BeanContainer beanContainer = SingletonBeanContainer.getInstance();
    private final FactoryBeanContainer factoryBeanContainer = SingletonFactoryBeanContainer.getInstance();

    private JavaBeanFactory(Class<?>... configurationClasses) throws ReflectiveOperationException {
        register(configurationClasses);
    }

    public static BeanFactory create(Class<?>... configurationClasses) throws ReflectiveOperationException {
        return new JavaBeanFactory(configurationClasses);
    }
}
