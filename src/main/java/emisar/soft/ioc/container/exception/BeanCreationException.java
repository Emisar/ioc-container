package emisar.soft.ioc.container.exception;

public class BeanCreationException extends BeansException {
    public BeanCreationException(String message) {
        super("Can not create bean. Message: %s".formatted(message));
    }
}
