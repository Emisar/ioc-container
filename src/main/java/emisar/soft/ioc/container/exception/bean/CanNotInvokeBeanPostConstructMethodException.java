package emisar.soft.ioc.container.exception.bean;

public class CanNotInvokeBeanPostConstructMethodException extends AbstractBeansException {
    public CanNotInvokeBeanPostConstructMethodException(String name, String methodName) {
        super("Can not invoke bean with class %s post construct method %s.".formatted(name, methodName));
    }
}
