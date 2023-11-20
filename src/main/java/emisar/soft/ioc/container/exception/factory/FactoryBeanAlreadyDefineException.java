package emisar.soft.ioc.container.exception.factory;

public class FactoryBeanAlreadyDefineException extends AbstractFactoryBeansException {
    public FactoryBeanAlreadyDefineException(String name) {
        super("Factory bean with name %s is already define.".formatted(name));
    }
}
