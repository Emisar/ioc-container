package emisar.soft.ioc.container.exception.factory;

import emisar.soft.ioc.container.exception.BeansException;

public abstract class AbstractFactoryBeansException extends BeansException {
    protected AbstractFactoryBeansException(String message) {
        super(message);
    }
}
