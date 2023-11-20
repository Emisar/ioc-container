package emisar.soft.ioc.container.context.impl;

import emisar.soft.ioc.container.configuration.ext.JavaBeanConfiguration;
import emisar.soft.ioc.container.container.BeanContainer;
import emisar.soft.ioc.container.container.FactoryBeanContainer;
import emisar.soft.ioc.container.container.impl.SingletonBeanContainer;
import emisar.soft.ioc.container.container.impl.SingletonFactoryBeanContainer;
import emisar.soft.ioc.container.context.ApplicationContext;
import emisar.soft.ioc.container.context.def.DefaultApplicationContext;
import lombok.Getter;

@Getter
public class JavaApplicationContext implements DefaultApplicationContext, JavaBeanConfiguration {

    private final BeanContainer beanContainer = SingletonBeanContainer.getInstance();
    private final FactoryBeanContainer factoryBeanContainer = SingletonFactoryBeanContainer.getInstance();

    private JavaApplicationContext(Class<?>... configurationClasses) throws ReflectiveOperationException {
        register(configurationClasses);
        registerBeans();
    }

    public static ApplicationContext create(Class<?>... configurationClasses) throws ReflectiveOperationException {
        return new JavaApplicationContext(configurationClasses);
    }
}
