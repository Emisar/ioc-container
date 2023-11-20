package emisar.soft.ioc.container.exception;

public abstract class BeansException extends RuntimeException {
    protected BeansException(String message) {
        super(message);
    }
}
