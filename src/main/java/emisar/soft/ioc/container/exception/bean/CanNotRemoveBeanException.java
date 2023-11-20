package emisar.soft.ioc.container.exception.bean;

public class CanNotRemoveBeanException extends AbstractBeansException {
    public CanNotRemoveBeanException(String beanName) {
        super("Can not remove bean with name %s.".formatted(beanName));
    }
}
