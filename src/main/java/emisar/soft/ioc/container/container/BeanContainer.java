package emisar.soft.ioc.container.container;

import emisar.soft.ioc.container.exception.bean.*;

import java.util.List;

/**
 * An object that contains beans. Each bean has a specified key that contain name and class of the bean.
 * A container can not contain duplicate keys - each key can associated to at most one bean.
 *
 * @author Anton Sorokin
 */
public interface BeanContainer {

    String CONFIGURATION_BEAN_PREFIX = "%";

    /**
     * Associates the specified bean with the specified key(name and class) in this container.
     * If the container previously contained a bean for key, the {@link BeanAlreadyDefineException} will throw.
     *
     * @param beanName part of the key with which the specified bean is to be associated
     * @param bean     bean to be associated with the specified key
     * @return the new bean associated with specified key
     * @throws BeanAlreadyDefineException if the container previously contained a bean for key
     */
    Object addBean(String beanName, Object bean) throws AbstractBeansException;

    /**
     * Returns the bean to which the specified key is mapped.
     *
     * @param beanName the part of the key whose associated bean is to be returned
     * @return the bean to which the specified key is associated
     * @throws NoUniqueBeanDefinitionException if a name is associated with more than one bean
     * @throws NoSuchBeanException             if the name is not associated with any bean
     */
    Object getBean(String beanName) throws AbstractBeansException;

    /**
     * Returns the bean to which the specified key is mapped.
     *
     * @param requiredType the part of the key whose associated bean is to be returned
     * @param <T>          the expected type of bean
     * @return the bean to which the specified key is associated
     * @throws NoUniqueBeanDefinitionException if a name is associated with more than one bean
     * @throws NoSuchBeanException             if the name is not associated with any bean
     */
    <T> T getBean(Class<T> requiredType) throws AbstractBeansException;

    /**
     * Returns the bean to which the specified key is mapped.
     *
     * @param beanName     the part of the key whose associated bean is to be returned
     * @param requiredType the part of the key whose associated bean is to be returned
     * @param <T>          the expected type of bean
     * @return the bean to which the specified key is associated
     * @throws NoUniqueBeanDefinitionException if a name is associated with more than one bean
     * @throws NoSuchBeanException             if the name is not associated with any bean
     * @throws WrongBeanTypeException          if the bean type does not match the expected one
     */
    <T> T getBean(String beanName, Class<T> requiredType) throws AbstractBeansException;

    /**
     * Returns all beans contained in the container.
     *
     * @return list of beans
     */
    List<Object> getAllBeans();

    /**
     * Removes the association for a key from this container if it is present.
     *
     * @param beanName the part of the key whose associated bean is to be removed
     * @return the removed bean associated with specified key
     * @throws NoUniqueBeanDefinitionException if a name is associated with more than one bean
     * @throws NoSuchBeanException             if the name is not associated with any bean
     * @throws CanNotRemoveBeanException       if the bean can not be removed for some other reasons
     */
    Object removeBeam(String beanName) throws AbstractBeansException;

    /**
     * Returns {@code true} if this container contains an association for the specified key.
     *
     * @param beanName the part of the key whose associated bean is to be contained
     * @return {@code true} if this container contains an association for the specified key
     */
    boolean containsBean(String beanName);

    /**
     * Returns {@code true} if this container contains an association for the specified key.
     *
     * @param requiredType the part of the key whose associated bean is to be contained
     * @return {@code true} if this container contains an association for the specified key
     */
    boolean containsBean(Class<?> requiredType);
}
