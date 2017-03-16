package ro.blackjackweb.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by tekin.omer on 8/24/2015.
 */
public class AppProperties {
    public static final String SPRING_PROFILE = "spring.profiles.active";
    public static final String EHCACHE_RMI_ADDRESS = "ehcache.rmi.address";
    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    public static MultiValueMap<String, String> NOT_MODIFIED_HEADERS;
    public static MultiValueMap<String, String> MILIS_HEADERS;
    static {
        NOT_MODIFIED_HEADERS = new HttpHeaders();
        NOT_MODIFIED_HEADERS.put("NOT-MODIFIED", Arrays.asList("1"));

        MILIS_HEADERS = new HttpHeaders();
        MILIS_HEADERS.put("MILIS", Arrays.asList("1"));
    }

    private String profile;
    private String ehCacheRmiAddress;

    private Properties appProps;

    private AppProperties() {
        this.profile = readProfileFromEnv();
        this.ehCacheRmiAddress = readEhCacheRmiAddressFromEnv();

        LOG.debug("Using profile: " + this.profile);
        try {
            appProps = new Properties();
            appProps.load(AppProperties.class.getClassLoader().getResourceAsStream("application-" + profile + ".properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * RPropertiesHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class RPropertiesHolder {
        public static final AppProperties INSTANCE = new AppProperties();
    }
    public static AppProperties getInstance() {
        return RPropertiesHolder.INSTANCE;
    }

    private static String readProfileFromEnv() {
        Object profile = System.getProperties().get(SPRING_PROFILE);
        if (profile != null) {
            return profile.toString();
        }
        return "dev";
    }
    private static String readEhCacheRmiAddressFromEnv() {
        Object address = System.getProperties().get(EHCACHE_RMI_ADDRESS);
        if (address != null) {
            return address.toString();
        }
        return null;
    }

    public String getEhCacheRmiAddress() {
        return ehCacheRmiAddress;
    }

    public String getProfile() {
        return profile;
    }

    public Properties getAppProps() {
        return appProps;
    }

    public String getProperty(String key) {
        return this.appProps.getProperty(key);
    }

    public String getRequiredProperty(String key) {
        String property = this.appProps.getProperty(key);
        if (property == null) {
            throw new RuntimeException("Property " + key + " not found!");
        }
        return property;
    }

}
