package emisar.soft.ioc.container.factory;

import emisar.soft.ioc.container.exception.bean.AbstractBeansException;

public interface BeanFactory {

    Object getBean(String beanName) throws AbstractBeansException;

    <T> T getBean(Class<T> requiredType) throws AbstractBeansException;

    <T> T getBean(String beanName, Class<T> requiredType) throws AbstractBeansException;

    boolean containsBean(String beanName);

    boolean containsBean(Class<?> requiredType);
}
