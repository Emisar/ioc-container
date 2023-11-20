package emisar.soft.ioc.container.entity.impl;

import emisar.soft.ioc.container.entity.FactoryBean;
import emisar.soft.ioc.container.exception.BeanCreationException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;

@Getter
@RequiredArgsConstructor
public class MethodFactoryBean implements FactoryBean {

    private final String factoryName;
    private final Method factoryFunction;

    @Override
    public boolean isParentNeeded() {
        return true;
    }

    @Override
    public Class<?> getReturnType() {
        return factoryFunction.getReturnType();
    }

    @Override
    public Object createBean(Object parent, Object[] params) throws BeanCreationException {
        try {
            return factoryFunction.invoke(parent, params);
        } catch (ReflectiveOperationException e) {
            throw new BeanCreationException(e.getMessage());
        }
    }
}
