package emisar.soft.ioc.container.exception.bean;

import emisar.soft.ioc.container.util.Assert;

public class WrongBeanTypeException extends AbstractBeansException {
    public WrongBeanTypeException(String beanName, Class<?> requiredType, Class<?> actualType) {
        super("Bean with name %s has a different type. Expected type %s, actual type %s.".formatted(
                beanName, Assert.getTypeName(requiredType), Assert.getTypeName(actualType)
        ));
    }
}
