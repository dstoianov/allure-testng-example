package config.properties;

//import ru.yandex.qatools.config.properties.PropertyLoader;
//import ru.yandex.qatools.config.properties.annotations.Property;
//import ru.yandex.qatools.config.properties.annotations.Resource;

import lombok.Getter;
import ru.qatools.properties.Property;
import ru.qatools.properties.PropertyLoader;
import ru.qatools.properties.Resource;

/**
 * Created by Funker on 01.01.2015.
 */

// since ver 1.6 @Resource.Classpath({"a.config.properties", "b.config.properties"})
@Getter
@Resource.Classpath("proxy.properties")
public class ProxyProperties {

    private static ProxyProperties instance;

    public static ProxyProperties getInstance() {
        if (instance == null) {
            instance = new ProxyProperties();
            PropertyLoader.newInstance().populate(instance);
        }
        return instance;
    }

    @Property("proxy.host")
    private String host = "localhost";

    @Property("proxy.port")
    private int port;

    @Property("proxy.active")
    private boolean active;

    @Property("proxy.ip")
    private String ip = "127.0.0.1";

//    public ProxyProperties() {
//        PropertyLoader.newInstance().populate(this);
//    }

//    public String getHost() {
//        return host;
//    }
//
//    public int getPort() {
//        return port;
//    }
//
//    public boolean isActive() {
//        return active;
//    }
//
//    public String getIp() {
//        return ip;
//    }
}
