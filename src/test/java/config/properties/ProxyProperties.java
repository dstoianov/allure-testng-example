package config.properties;

import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;

/**
 * Created by Funker on 01.01.2015.
 */

// since ver 1.6 @Resource.Classpath({"a.properties", "b.properties"})
@Resource.Classpath("proxy.properties")
public class ProxyProperties {

    @Property("proxy.host")
    private String host = "localhost";

    @Property("proxy.port")
    private int port;

    @Property("proxy.active")
    private boolean active;

    @Property("proxy.ip")
    private String ip = "127.0.0.1";

    public ProxyProperties() {
        PropertyLoader.populate(this);
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public boolean isActive() {
        return active;
    }

    public String getIp() {
        return ip;
    }
}
