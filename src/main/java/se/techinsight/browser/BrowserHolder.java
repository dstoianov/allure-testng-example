package se.techinsight.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class BrowserHolder {

    private static final Map<Long, WebDriver> DRIVER_MAP = new ConcurrentHashMap<>();

    public static void init() {
        String browser = System.getProperty("browser", "chrome");
//        boolean remote = Boolean.parseBoolean(System.getProperty("remote", "false"));
        boolean remote = true;

        log.info("Going to use '{}' browser", browser);
        if (remote) {
            DRIVER_MAP.putIfAbsent(Thread.currentThread().getId(), getRemoteWd(browser));
        } else if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            DRIVER_MAP.putIfAbsent(Thread.currentThread().getId(), new ChromeDriver());
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            DRIVER_MAP.putIfAbsent(Thread.currentThread().getId(), new FirefoxDriver());
        } else {
            throw new RuntimeException("Unknown browser name " + browser);
        }
    }

    @SneakyThrows
    private static RemoteWebDriver getRemoteWd(String browserName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browserName);
//      capabilities.setVersion("79.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);

        return new RemoteWebDriver(URI.create("http://192.168.178.100:4444/wd/hub").toURL(), capabilities);
    }

    public static WebDriver getDriver() {
        return DRIVER_MAP.get(Thread.currentThread().getId());
    }

    public static void quit() {
        if (Objects.nonNull(getDriver())) {
            getDriver().quit();
        }
        DRIVER_MAP.remove(Thread.currentThread().getId());
    }
}
