package emisar.soft.ioc.container.container.impl;

import emisar.soft.ioc.container.container.BeanContainer;
import emisar.soft.ioc.container.container.def.DefaultBeanContainer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SingletonBeanContainer implements DefaultBeanContainer {

    private final Map<Map.Entry<String, Class<?>>, Object> beanMap;

    private static class SingletonHelper {
        private static final SingletonBeanContainer INSTANCE = new SingletonBeanContainer(new HashMap<>());
    }

    public static BeanContainer getInstance() {
        return SingletonBeanContainer.SingletonHelper.INSTANCE;
    }
}
