
package ch.h2m.poi.configuration;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author h2mch
 */
public class ConfiguratorProducer {


    /**
     * Define as system property
     * f.e.: connectionTimeout=500 and/or readTimeout=500
     */


    @Produces
    public long expose(InjectionPoint ip) {
        String fieldName = ip.getMember().getName();
        String configuredValue = System.getenv().getOrDefault(fieldName, "0");
        return Long.parseLong(configuredValue);
    }
}
