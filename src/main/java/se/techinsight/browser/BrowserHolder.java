package se.techinsight.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class BrowserHolder {

    private static final Map<Long, WebDriver> DRIVER_MAP = new ConcurrentHashMap<>();

    public static void init() {
        String browser = System.getProperty("browser", "chrome");
        if (browser.equalsIgnoreCase("chrome")) {
            log.info("Going to use '{}' browser", browser);
            WebDriverManager.chromedriver().setup();
            DRIVER_MAP.putIfAbsent(Thread.currentThread().getId(), new ChromeDriver());
        } else if (browser.equalsIgnoreCase("firefox")) {
            log.info("Going to use '{}' browser", browser);
            WebDriverManager.firefoxdriver().setup();
            DRIVER_MAP.putIfAbsent(Thread.currentThread().getId(), new FirefoxDriver());
        } else {
            throw new RuntimeException("Unknown browser name " + browser);
        }
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
