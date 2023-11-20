package emisar.soft.ioc.container.exception.factory;

import emisar.soft.ioc.container.util.Assert;

import java.util.Set;

public class NoUniqueFactoryBeanDefinitionException extends AbstractFactoryBeansException {
    public NoUniqueFactoryBeanDefinitionException(String name) {
        super("Factory bean with name %s is not unique.".formatted(name));
    }

    public NoUniqueFactoryBeanDefinitionException(Class<?> requiredReturnedType, Set<String> beansName) {
        super("Factory bean with required returned type %s is not unique. All matches: %s".formatted(
                Assert.getTypeName(requiredReturnedType), Assert.collectionToString(beansName))
        );
    }
}
