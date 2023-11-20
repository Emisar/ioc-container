package emisar.soft.ioc.container.container;

import emisar.soft.ioc.container.entity.FactoryBean;
import emisar.soft.ioc.container.exception.bean.BeanAlreadyDefineException;
import emisar.soft.ioc.container.exception.factory.*;

import java.lang.reflect.Executable;
import java.util.List;

/**
 * An object that contains factory beans. Each factory bean has a specified key that contain name and returned type of the bean.
 * A container can not contain duplicate keys - each key can associated to at most one factory bean.
 *
 * @author Anton Sorokin
 */
public interface FactoryBeanContainer {

    String FACTORY_BEAN_PREFIX = "&";

    /**
     * Associates the specified bean with the specified key(name and class) in this container.
     * If the container previously contained a bean for key, the {@link BeanAlreadyDefineException} will throw.
     *
     * @param factoryBeanName part of the key with which the specified factory bean is to be associated
     * @param factoryBean     factory bean to be associated with the specified key
     * @return the new factory bean associated with specified key
     * @throws FactoryBeanAlreadyDefineException if the container previously contained a factory bean for key
     */
    <T extends Executable> FactoryBean addFactoryBean(String factoryBeanName, T factoryBean) throws AbstractFactoryBeansException;

    /**
     * Returns the factory bean to which the specified key is mapped.
     *
     * @param factoryBeanName the part of the key whose associated factory bean is to be returned
     * @return the factory bean to which the specified key is associated
     * @throws NoUniqueFactoryBeanDefinitionException if a name is associated with more than one factory bean
     * @throws NoSuchFactoryBeanException             if the name is not associated with any factory bean
     */
    FactoryBean getFactoryBean(String factoryBeanName) throws AbstractFactoryBeansException;

    /**
     * Returns the factory bean to which the specified key is mapped.
     *
     * @param requiredReturnType the part of the key whose associated factory bean is to be returned
     * @return the factory bean to which the specified key is associated
     * @throws NoUniqueFactoryBeanDefinitionException if a name is associated with more than one factory bean
     * @throws NoSuchFactoryBeanException             if the name is not associated with any factory bean
     */
    FactoryBean getFactoryBean(Class<?> requiredReturnType) throws AbstractFactoryBeansException;

    /**
     * Returns all factory beans contained in the container.
     *
     * @return list of factory beans
     */
    List<FactoryBean> getAllFactoryBeans();

    /**
     * Removes the association for a key from this container if it is present.
     *
     * @param factoryBeanName the part of the key whose associated factory bean is to be removed
     * @return the removed factory bean associated with specified key
     * @throws NoUniqueFactoryBeanDefinitionException if a name is associated with more than one factory bean
     * @throws NoSuchFactoryBeanException             if the name is not associated with any factory bean
     * @throws CanNotRemoveFactoryBeanException       if the factory bean can not be removed for some other reasons
     */
    FactoryBean removeFactoryBean(String factoryBeanName) throws AbstractFactoryBeansException;

    /**
     * Returns {@code true} if this container contains an association for the specified key.
     *
     * @param factoryBeanName the part of the key whose associated factory bean is to be contained
     * @return {@code true} if this container contains an association for the specified key
     */
    boolean containsFactoryBean(String factoryBeanName);

    /**
     * Returns {@code true} if this container contains an association for the specified key.
     *
     * @param requiredReturnType the part of the key whose associated factory bean is to be contained
     * @return {@code true} if this container contains an association for the specified key
     */
    boolean containsFactoryBean(Class<?> requiredReturnType);
}
