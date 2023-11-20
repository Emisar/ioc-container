package emisar.soft.ioc.container.container.def;

import emisar.soft.ioc.container.container.BeanContainer;
import emisar.soft.ioc.container.exception.bean.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class provides a skeletal implementation of the {@code BeanContainer}
 * interface, to minimize the effort required to implement this interface.
 *
 * @author Anton Sorokin
 * @see BeanContainer
 */
public interface DefaultBeanContainer extends BeanContainer {

    Map<Map.Entry<String, Class<?>>, Object> getBeanMap();

    @Override
    default Object addBean(String beanName, Object bean) throws AbstractBeansException {
        if (containsBean(beanName)) throw new BeanAlreadyDefineException(beanName);
        Map.Entry<String, Class<?>> key = Map.entry(beanName, bean.getClass());
        if (Objects.nonNull(getBeanMap().put(key, bean))) throw new BeanAlreadyDefineException(beanName);
        return bean;
    }

    @Override
    default Object getBean(String beanName) throws AbstractBeansException {
        List<Object> beanList = getBeanMap().entrySet().stream().parallel()
                .filter(e -> e.getKey().getKey().equals(beanName))
                .map(Map.Entry::getValue)
                .toList();
        if (beanList.size() > 1) throw new NoUniqueBeanDefinitionException(beanName);
        if (beanList.isEmpty()) throw new NoSuchBeanException(beanName);
        return beanList.getFirst();
    }

    @Override
    default <T> T getBean(Class<T> requiredType) throws AbstractBeansException {
        Map<Map.Entry<String, Class<?>>, Object> filteredBeanMap = getBeanMap().entrySet().stream().parallel()
                .filter(e -> requiredType.isAssignableFrom(e.getKey().getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (filteredBeanMap.size() > 1) throw new NoUniqueBeanDefinitionException(
                requiredType,
                filteredBeanMap.keySet().parallelStream()
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toSet())
        );
        return filteredBeanMap.entrySet().stream()
                .findFirst()
                .map(Map.Entry::getValue)
                .map(requiredType::cast)
                .orElseThrow(() -> new NoSuchBeanException(requiredType));
    }

    @Override
    default <T> T getBean(String beanName, Class<T> requiredType) throws AbstractBeansException {
        Object bean = getBean(beanName);
        if (requiredType.isAssignableFrom(bean.getClass()))
            throw new WrongBeanTypeException(beanName, requiredType, bean.getClass());
        return requiredType.cast(bean);
    }

    @Override
    default List<Object> getAllBeans() {
        return getBeanMap().values().parallelStream().toList();
    }

    @Override
    default Object removeBeam(String beanName) throws AbstractBeansException {
        if (!containsBean(beanName)) throw new NoSuchBeanException(beanName);
        Object bean = getBean(beanName);
        if (!getBeanMap().remove(Map.entry(beanName, bean.getClass()), bean))
            throw new CanNotRemoveBeanException(beanName);
        return bean;
    }

    @Override
    default boolean containsBean(String beanName) {
        return getBeanMap().entrySet().stream().parallel()
                .anyMatch(e -> e.getKey().getKey().equals(beanName));
    }

    @Override
    default boolean containsBean(Class<?> requiredType) {
        return getBeanMap().entrySet().stream().parallel()
                .anyMatch(e -> requiredType.isAssignableFrom(e.getKey().getValue()));
    }
}
