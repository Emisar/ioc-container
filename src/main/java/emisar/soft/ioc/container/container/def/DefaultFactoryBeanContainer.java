package emisar.soft.ioc.container.container.def;

import emisar.soft.ioc.container.container.FactoryBeanContainer;
import emisar.soft.ioc.container.entity.impl.ConstructorFactoryBean;
import emisar.soft.ioc.container.entity.FactoryBean;
import emisar.soft.ioc.container.entity.impl.MethodFactoryBean;
import emisar.soft.ioc.container.exception.bean.AbstractBeansException;
import emisar.soft.ioc.container.exception.factory.*;

import java.lang.reflect.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class provides a skeletal implementation of the {@code FactoryBeanContainer}
 * interface, to minimize the effort required to implement this interface.
 *
 * @author Anton Sorokin
 * @see FactoryBeanContainer
 */
public interface DefaultFactoryBeanContainer extends FactoryBeanContainer {

    Map<Map.Entry<String, Class<?>>, FactoryBean> getFactoryBeanMap();

    @Override
    default <T extends Executable> FactoryBean addFactoryBean(String factoryBeanName, T factoryFunction) throws AbstractFactoryBeansException {
        if (containsFactoryBean(factoryBeanName)) throw new FactoryBeanAlreadyDefineException(factoryBeanName);
        FactoryBean factoryBean = switch (factoryFunction) {
            case Method method -> new MethodFactoryBean(factoryBeanName, method);
            case Constructor<?> constructor -> new ConstructorFactoryBean(factoryBeanName, constructor);
        };
        Map.Entry<String, Class<?>> key = Map.entry(factoryBeanName, factoryBean.getReturnType());
        if (Objects.nonNull(getFactoryBeanMap().put(key, factoryBean)))
            throw new FactoryBeanAlreadyDefineException(factoryBeanName);
        return factoryBean;
    }

    @Override
    default FactoryBean getFactoryBean(String factoryBeanName) throws AbstractFactoryBeansException {
        List<FactoryBean> factoryBeanList = getFactoryBeanMap().entrySet().stream().parallel()
                .filter(e -> e.getKey().getKey().equals(factoryBeanName))
                .map(Map.Entry::getValue)
                .toList();
        if (factoryBeanList.size() > 1) throw new NoUniqueFactoryBeanDefinitionException(factoryBeanName);
        if (factoryBeanList.isEmpty()) throw new NoSuchFactoryBeanException(factoryBeanName);
        return factoryBeanList.getFirst();
    }

    @Override
    default FactoryBean getFactoryBean(Class<?> requiredReturnType) throws AbstractFactoryBeansException {
        Map<Map.Entry<String, Class<?>>, FactoryBean> filteredFactoryBeanMap = getFactoryBeanMap().entrySet().stream().parallel()
                .filter(e -> requiredReturnType.isAssignableFrom(e.getKey().getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (filteredFactoryBeanMap.size() > 1) throw new NoUniqueFactoryBeanDefinitionException(
                requiredReturnType,
                filteredFactoryBeanMap.keySet().parallelStream()
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toSet())
        );
        return filteredFactoryBeanMap.entrySet().stream()
                .findFirst()
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new NoSuchFactoryBeanException(requiredReturnType));
    }

    @Override
    default List<FactoryBean> getAllFactoryBeans() throws AbstractBeansException {
        return getFactoryBeanMap().values().parallelStream().toList();
    }

    @Override
    default FactoryBean removeFactoryBean(String factoryBeanName) throws AbstractFactoryBeansException {
        if (!containsFactoryBean(factoryBeanName)) throw new NoSuchFactoryBeanException(factoryBeanName);
        FactoryBean factoryBean = getFactoryBean(factoryBeanName);
        if (!getFactoryBeanMap().remove(Map.entry(factoryBeanName, factoryBean.getClass()), factoryBean))
            throw new CanNotRemoveFactoryBeanException(factoryBeanName);
        return factoryBean;
    }

    @Override
    default boolean containsFactoryBean(String factoryBeanName) {
        return getFactoryBeanMap().entrySet().stream().parallel()
                .anyMatch(e -> e.getKey().getKey().equals(factoryBeanName));
    }

    @Override
    default boolean containsFactoryBean(Class<?> requiredReturnType) {
        return getFactoryBeanMap().entrySet().stream().parallel()
                .anyMatch(e -> requiredReturnType.isAssignableFrom(e.getKey().getValue()));
    }
}
