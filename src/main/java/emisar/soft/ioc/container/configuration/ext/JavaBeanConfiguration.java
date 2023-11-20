package emisar.soft.ioc.container.configuration.ext;

import emisar.soft.ioc.container.annotation.AdditionalConfiguration;
import emisar.soft.ioc.container.annotation.Bean;
import emisar.soft.ioc.container.annotation.Configuration;
import emisar.soft.ioc.container.configuration.BeanConfiguration;
import emisar.soft.ioc.container.container.BeanContainer;
import emisar.soft.ioc.container.container.FactoryBeanContainer;
import emisar.soft.ioc.container.exception.MoreThanOneConstructorForBeanException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static emisar.soft.ioc.container.container.BeanContainer.CONFIGURATION_BEAN_PREFIX;
import static emisar.soft.ioc.container.container.FactoryBeanContainer.FACTORY_BEAN_PREFIX;

public interface JavaBeanConfiguration extends BeanConfiguration {

    BeanContainer getBeanContainer();

    FactoryBeanContainer getFactoryBeanContainer();

    default void register(Class<?>... configurationClasses) throws ReflectiveOperationException {
        configure(registerConfigurationBeans(configurationClasses).toArray());
    }

    private List<Object> registerConfigurationBeans(Class<?>[] configurationClasses) throws ReflectiveOperationException {
        if (Objects.isNull(configurationClasses)) throw new NullPointerException();
        List<Object> result = new ArrayList<>();
        for (Class<?> configurationClass : configurationClasses) {
            if (!configurationClass.isAnnotationPresent(Configuration.class)) continue;
            result.addAll(registerConfigurationBean(configurationClass));
        }
        return result;
    }

    private List<Object> registerConfigurationBean(Class<?> configurationClass) throws ReflectiveOperationException {
        if (Objects.isNull(configurationClass)) throw new NullPointerException();
        List<Object> result = new ArrayList<>();
        Constructor<?> configNoArgsConstructor = configurationClass.getConstructor();
        Object configObject = configNoArgsConstructor.newInstance();
        result.add(getBeanContainer().addBean(
                CONFIGURATION_BEAN_PREFIX + configurationClass.getTypeName(),
                configObject
        ));
        // Register additional configuration classes
        if (configurationClass.isAnnotationPresent(AdditionalConfiguration.class)) {
            AdditionalConfiguration annotation = configurationClass.getAnnotation(AdditionalConfiguration.class);
            result.addAll(registerConfigurationBeans(annotation.value()));
        }
        return result;
    }

    @Override
    default void configure(Object[] configurationClasses) {
        Arrays.stream(configurationClasses)
                .map(Object::getClass)
                .forEach(this::registerFactoryBeans);
    }

    private void registerFactoryBeans(Class<?> configurationBeanType) {
        if (Objects.isNull(configurationBeanType)) throw new NullPointerException();
        Field[] fields = configurationBeanType.getFields();
        Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(Bean.class))
                .forEach(field -> {
                    Constructor<?>[] constructors = field.getType().getConstructors();
                    if (constructors.length > 1) throw new MoreThanOneConstructorForBeanException(field.getName());
                    getFactoryBeanContainer().addFactoryBean(FACTORY_BEAN_PREFIX + field.getName(), constructors[0]);
                });
        Method[] methods = configurationBeanType.getMethods();
        Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(Bean.class))
                .forEach(method -> getFactoryBeanContainer().addFactoryBean(FACTORY_BEAN_PREFIX + method.getName(), method));
    }
}
