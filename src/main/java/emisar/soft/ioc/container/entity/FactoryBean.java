package emisar.soft.ioc.container.entity;

import emisar.soft.ioc.container.exception.BeanCreationException;

import java.lang.reflect.Executable;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public interface FactoryBean {

    String getFactoryName();

    <T extends Executable> T getFactoryFunction();

    boolean isParentNeeded();

    Class<?> getReturnType();

    Object createBean(Object parent, Object[] params) throws BeanCreationException;

    default Parameter[] getParameters() {
        return getFactoryFunction().getParameters();
    }

    default Class<?> getParentClass() {
        return getFactoryFunction().getDeclaringClass();
    }
}