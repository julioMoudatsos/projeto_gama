package parallel.config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static parallel.utils.Propriedades.*;

public class DriverFactory {
    public  static  final Boolean grid = true;
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal(){
        @Override
        protected synchronized WebDriver initialValue(){
            return getDriver();
        }
    };

    public DriverFactory() {
    }

    public static WebDriver getDriver() {

        return  driverThreadLocal.get();

    }

    public static void setDriver(WebDriver driver) {
        if(GRID){
            FirefoxOptions options = new FirefoxOptions();
            options.setCapability("browserName", "firefox");
            options.setCapability("platformName", "WINDOWS");
            options.addArguments("--headless");
            try {
                driver = new RemoteWebDriver(new URL("http://192.168.0.104:4444"),options);
                driverThreadLocal.set(driver);
            } catch (MalformedURLException e) {
                System.err.println("#################Falha na conexão com o GRID#################");
                e.printStackTrace();
            }
        }

        if(LOCAL){
            driverThreadLocal.set(driver);
        }

        if(NUVEM){
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            String date = currentDate.format(dateFormatter);
            String time = LocalTime.now().format(timeFormatter);

            FirefoxOptions options = new FirefoxOptions();
            options.setCapability("browserName", "firefox");
            options.setCapability("platformName", "WINDOWS");
            options.addArguments("--headless");

            Map<String, Object> sauceOptions = new HashMap<>();
            sauceOptions.put("build", "selenium-build-41F9G");
            sauceOptions.put("name", "PJ_OMEGA_" + date+"-"+time);
            sauceOptions.put("recordVideo", true);
            options.setCapability("sauce:options", sauceOptions);

            try {
                driver = new RemoteWebDriver(new URL(""),options);
                driverThreadLocal.set(driver);
            } catch (MalformedURLException e) {
                System.err.println("#################Falha na conexão com a NUVEM#################");
                e.printStackTrace();
            }
        }
    }

    public static void closeDriver()  {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}

