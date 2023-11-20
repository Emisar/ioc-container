package emisar.soft.ioc.container.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Assert {
    public static String getTypeName(Class<?> requiredType) {
        if (Objects.isNull(requiredType)) throw new NullPointerException();
        return requiredType.getTypeName();
    }

    public static String collectionToString(Collection<?> collection) {
        if (Objects.isNull(collection)) throw new NullPointerException();
        return collection.toString();
    }
}
