package emisar.soft.ioc.container.context.def;

import emisar.soft.ioc.container.annotation.PostConstruct;
import emisar.soft.ioc.container.context.ApplicationContext;
import emisar.soft.ioc.container.entity.FactoryBean;
import emisar.soft.ioc.container.exception.bean.CanNotInvokeBeanPostConstructMethodException;
import emisar.soft.ioc.container.processor.BeanPostProcessor;

import java.lang.reflect.Method;

public interface DefaultApplicationContext extends ApplicationContext, BeanPostProcessor {

    @Override
    default Object createBean(FactoryBean factoryBean) {
        Object bean = ApplicationContext.super.createBean(factoryBean);
        executeBeanPostConstruct(bean);
        return bean;
    }

    default void registerBeans() {
        getFactoryBeanContainer().getAllFactoryBeans().stream()
                .map(FactoryBean::getReturnType)
                .forEach(this::getBean);
    }

    @Override
    default void executeBeanPostConstruct(Object bean) {
        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                try {
                    method.invoke(bean);
                } catch (ReflectiveOperationException exception) {
                    throw new CanNotInvokeBeanPostConstructMethodException(bean.getClass().getTypeName(), method.getName());
                }
            }
        }
    }
}
