package emisar.soft.ioc.container.factory.def;

import emisar.soft.ioc.container.container.BeanContainer;
import emisar.soft.ioc.container.container.FactoryBeanContainer;
import emisar.soft.ioc.container.entity.FactoryBean;
import emisar.soft.ioc.container.exception.BeansException;
import emisar.soft.ioc.container.exception.bean.AbstractBeansException;
import emisar.soft.ioc.container.exception.bean.WrongBeanTypeException;
import emisar.soft.ioc.container.factory.BeanFactory;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static emisar.soft.ioc.container.container.FactoryBeanContainer.FACTORY_BEAN_PREFIX;

public interface DefaultBeanFactory extends BeanFactory {

    BeanContainer getBeanContainer();

    FactoryBeanContainer getFactoryBeanContainer();

    default Object createBean(FactoryBean factoryBean) {
        Parameter[] factoryBeanParameters = factoryBean.getParameters();
        Object[] factoryBeanParametersObjects = getParameters(factoryBeanParameters);
        Object factoryBeanParent = getParent(factoryBean);
        return factoryBean.createBean(factoryBeanParent, factoryBeanParametersObjects);
    }

    @Override
    default Object getBean(String beanName) throws AbstractBeansException {
        if (containsBean(beanName)) {
            return getBeanContainer().getBean(beanName);
        } else {
            FactoryBean factoryBean = getFactoryBeanContainer().getFactoryBean(FACTORY_BEAN_PREFIX + beanName);
            Object bean = createBean(factoryBean);
            return getBeanContainer().addBean(beanName, bean);
        }
    }

    @Override
    default <T> T getBean(Class<T> requiredType) throws AbstractBeansException {
        if (containsBean(requiredType)) {
            return getBeanContainer().getBean(requiredType);
        } else {
            FactoryBean factoryBean = getFactoryBeanContainer().getFactoryBean(requiredType);
            Object bean = createBean(factoryBean);
            return requiredType.cast(getBeanContainer().addBean(factoryBean.getFactoryName().substring(1), bean));
        }
    }

    @Override
    default <T> T getBean(String beanName, Class<T> requiredType) throws AbstractBeansException {
        if (containsBean(beanName)) {
            return getBeanContainer().getBean(beanName, requiredType);
        } else {
            Object bean = getBean(beanName);
            if (requiredType.isInstance(bean.getClass()))
                throw new WrongBeanTypeException(beanName, requiredType, bean.getClass());
            return requiredType.cast(bean);
        }
    }

    @Override
    default boolean containsBean(String beanName) {
        return getBeanContainer().containsBean(beanName);
    }

    @Override
    default boolean containsBean(Class<?> requiredType) {
        return getBeanContainer().containsBean(requiredType);
    }

    private Object[] getParameters(Parameter[] factoryBeanParameters) {
        if (Objects.isNull(factoryBeanParameters)) throw new NullPointerException();
        List<Object> factoryBeanParametersObjects = new ArrayList<>();
        for (Parameter factoryBeanParameter : factoryBeanParameters) {
            Object factoryBeanParameterObject;
            try {
                String factoryBeanParameterName = factoryBeanParameter.getName();
                factoryBeanParameterObject = getBean(factoryBeanParameterName);
            } catch (BeansException exception) {
                Class<?> factoryBeanParameterType = factoryBeanParameter.getType();
                factoryBeanParameterObject = getBean(factoryBeanParameterType);
            }
            factoryBeanParametersObjects.add(factoryBeanParameterObject);
        }
        return factoryBeanParametersObjects.toArray();
    }

    private Object getParent(FactoryBean factoryBean) {
        if (Objects.isNull(factoryBean)) throw new NullPointerException();
        if (factoryBean.isParentNeeded()) {
            Class<?> parentClass = factoryBean.getParentClass();
            return getBean(parentClass);
        }
        return null;
    }
}
