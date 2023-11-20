package emisar.soft.ioc.container.exception.factory;

import emisar.soft.ioc.container.util.Assert;

public class NoSuchFactoryBeanException extends AbstractFactoryBeansException {
    public NoSuchFactoryBeanException(String name) {
        super("No such factory bean with name %s.".formatted(name));
    }

    public NoSuchFactoryBeanException(Class<?> requiredReturnedType) {
        super("No such factory bean with required return type %s.".formatted(Assert.getTypeName(requiredReturnedType)));
    }
}
