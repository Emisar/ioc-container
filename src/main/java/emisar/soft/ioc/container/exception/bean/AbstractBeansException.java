package emisar.soft.ioc.container.exception.bean;

import emisar.soft.ioc.container.exception.BeansException;

public abstract class AbstractBeansException extends BeansException {
    protected AbstractBeansException(String message) {
        super(message);
    }
}
