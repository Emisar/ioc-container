package emisar.soft.ioc.container.exception.factory;

public class CanNotRemoveFactoryBeanException extends AbstractFactoryBeansException {
    public CanNotRemoveFactoryBeanException(String beanName) {
        super("Can not remove factory bean with name %s.".formatted(beanName));
    }
}
