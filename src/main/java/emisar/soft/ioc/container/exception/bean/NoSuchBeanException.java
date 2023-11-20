package emisar.soft.ioc.container.exception.bean;

import emisar.soft.ioc.container.util.Assert;

public class NoSuchBeanException extends AbstractBeansException{
    public NoSuchBeanException(String name) {
        super("No such bean with name %s.".formatted(name));
    }

    public NoSuchBeanException(Class<?> requiredType) {
        super("No such bean with required type %s.".formatted(Assert.getTypeName(requiredType)));
    }
}
