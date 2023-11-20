package emisar.soft.ioc.container.exception;

public class MoreThanOneConstructorForBeanException extends BeansException {
    public MoreThanOneConstructorForBeanException(String beanName) {
        super("More than one constructor has been found for bean with name %s".formatted(beanName));
    }
}
