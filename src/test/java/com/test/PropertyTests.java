package com.test;

import config.ProxyProperties;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by Funker on 01.01.2015.
 */
public class PropertyTests {

    @Test
    public void proxySettingsFromFileTest() throws Exception {
        ProxyProperties proxyProperties = new ProxyProperties();

        assertThat(proxyProperties.isActive(), equalTo(false));
        assertThat(proxyProperties.getHost(), equalTo("proxy.yandex.ru"));
        assertThat(proxyProperties.getPort(), is(3133));
        assertThat(proxyProperties.getIp(), is("127.0.0.1"));
    }


    @Test(dependsOnMethods = "proxySettingsFromFileTest")
    public void proxySettingsFromSystemPropertyTest() throws Exception {
        System.setProperty("proxy.active", "true");
        System.setProperty("proxy.host", "google.com");
        System.setProperty("proxy.port", "1234");

        ProxyProperties proxyProperties = new ProxyProperties();

        assertThat(proxyProperties.isActive(), equalTo(true));
        assertThat(proxyProperties.getHost(), equalTo("google.com"));
        assertThat(proxyProperties.getPort(), is(1234));
    }

}
