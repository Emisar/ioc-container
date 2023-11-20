package emisar.soft.ioc.container.exception.bean;

import emisar.soft.ioc.container.util.Assert;

import java.util.Set;

public class NoUniqueBeanDefinitionException extends AbstractBeansException {
    public NoUniqueBeanDefinitionException(String name) {
        super("Bean with name %s is not unique.".formatted(name));
    }

    public NoUniqueBeanDefinitionException(Class<?> requiredType, Set<String> beansName) {
        super("Bean with class %s is not unique. All matches: %s".formatted(
                Assert.getTypeName(requiredType), Assert.collectionToString(beansName))
        );
    }
}
