package emisar.soft.ioc.container.exception.bean;

public class BeanAlreadyDefineException extends AbstractBeansException {
    public BeanAlreadyDefineException(String name) {
        super("Bean with name %s is already define.".formatted(name));
    }
}
