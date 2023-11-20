package emisar.soft.ioc.container.processor;

public interface BeanPostProcessor {

    void executeBeanPostConstruct(Object bean) throws ReflectiveOperationException;
}
