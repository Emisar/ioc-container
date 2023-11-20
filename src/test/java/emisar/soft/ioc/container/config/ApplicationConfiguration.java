package emisar.soft.ioc.container.config;

import emisar.soft.ioc.container.annotation.AdditionalConfiguration;
import emisar.soft.ioc.container.annotation.Configuration;

@Configuration
@AdditionalConfiguration({
        BeanMethodConfiguration.class,
        BeanFieldConfiguration.class
})
public class ApplicationConfiguration {
}
