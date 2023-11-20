package emisar.soft.ioc.container.container.impl;

import emisar.soft.ioc.container.container.FactoryBeanContainer;
import emisar.soft.ioc.container.container.def.DefaultFactoryBeanContainer;
import emisar.soft.ioc.container.entity.FactoryBean;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SingletonFactoryBeanContainer implements DefaultFactoryBeanContainer {

    private final Map<Map.Entry<String, Class<?>>, FactoryBean> factoryBeanMap;

    private static class SingletonHelper {
        private static final SingletonFactoryBeanContainer INSTANCE = new SingletonFactoryBeanContainer(new HashMap<>());
    }

    public static FactoryBeanContainer getInstance() {
        return SingletonFactoryBeanContainer.SingletonHelper.INSTANCE;
    }
}
