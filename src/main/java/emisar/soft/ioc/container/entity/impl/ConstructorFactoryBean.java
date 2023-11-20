package emisar.soft.ioc.container.entity.impl;

import emisar.soft.ioc.container.entity.FactoryBean;
import emisar.soft.ioc.container.exception.BeanCreationException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Constructor;

@Getter
@RequiredArgsConstructor
public class ConstructorFactoryBean implements FactoryBean {

    private final String factoryName;
    private final Constructor<?> factoryFunction;

    @Override
    public boolean isParentNeeded() {
        return false;
    }

    @Override
    public Class<?> getReturnType() {
        return factoryFunction.getDeclaringClass();
    }

    @Override
    public Object createBean(Object parent, Object[] params) throws BeanCreationException {
        try {
            return factoryFunction.newInstance(params);
        } catch (ReflectiveOperationException e) {
            throw new BeanCreationException(e.getMessage());
        }
    }
}
