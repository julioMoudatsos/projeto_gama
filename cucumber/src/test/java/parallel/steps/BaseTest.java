package parallel.steps;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import parallel.config.DriverFactory;

import java.awt.*;
import java.text.Format;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static parallel.config.DriverFactory.closeDriver;
import static parallel.config.DriverFactory.getDriver;
import static parallel.utils.Propriedades.*;

public class BaseTest {
    public static WebDriver driver;
    static final String GREEN_TEXT = "\033[0;92m";  // define a cor verde
    static final String RED_TEXT = "\033[0;91m";  // define a cor verde
   static final String PURPLE_TEXT = "\033[0;95m";  // define a cor verde
   static final String BLUE_TEXT = "\033[0;94m";  // define a cor verde
   static final String RESET_TEXT = "\033[0m";     // define o reset para a cor padrão
    @BeforeAll
    public static void infosRunner(){
        System.out.println(RED_TEXT + "  _____  _____   ____       _ ______ _______ ____          _____          __  __          \n" +
                " |  __ \\|  __ \\ / __ \\     | |  ____|__   __/ __ \\        / ____|   /\\\\   |  \\/  |   /\\    \n" +
                " | |__) | |__) | |  | |    | | |__     | | | |  | |______| |  __   /  \\  | \\  / |  /  \\   \n" +
                " |  ___/|  _  /| |  | |_   | |  __|    | | | |  | |______| | |_ | / /\\\\ \\ | |\\/| | / //\\\\ \\  \n" +
                " | |    | | \\ \\| |__| | |__| | |____   | | | |__| |      | |__| |/ ____ \\| |  | |/// ____ \\\\ \n" +
                " |_|    |_|  \\_\\\\____/ \\____/|______|  |_|  \\____/        \\_____/_/    \\_\\_|  |_/_/    \\_\\\n" +
                "                                                                                          \n" +
                "                                                                                          "+"" + RESET_TEXT);

        String nome = "Julio Moudatsos";
        System.out.println(BLUE_TEXT + "Created by: " + nome + "" + RESET_TEXT);

        LocalDate currentDate = LocalDate.now();
        // Formatar a data e a hora de acordo com o padrão desejado
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String date = currentDate.format(dateFormatter);
        String time = LocalTime.now().format(timeFormatter);

        String ambiente = GRID ? "GRID" : LOCAL ? "LOCAL" : NUVEM ? "NUVEM" : "-----";

        System.out.println(PURPLE_TEXT+"+--------------------------------------------------+\n" +
                "|               INFO TEST REPORT                   |\n" +
                "+--------------------------------------------------+\n" +
                "|   Browser:                          " + BROWSER + "      |\n" +
                "|   Ambiente:                         " + ambiente + "       |\n" +
                "|   Date:                             " + date + "   |\n" +
                "|   Execution Time:                   " + time + "     |\n" +
                "+--------------------------------------------------+\n"+""+ RESET_TEXT);

    }
    @Before
    public void iniciar(){
        if(GRID){
            DriverFactory.setDriver(driver);
        }
        if(NUVEM){
            DriverFactory.setDriver(driver);
        }
        if(LOCAL){
            switch(BROWSER) {
                case "opera":
                    System.setProperty(operaDriver, operaDriverPath);
                    ChromeOptions options = new ChromeOptions();
                    options.setCapability("browserName", "Opera");
                    options.setCapability("platformName", "WINDOWS");
                    options.addArguments("--headless");
                    driver = new OperaDriver(options);
                    break;
                case "fireFox":
                    System.setProperty(firefoxDriver, firefoxDriverPath);
                    FirefoxOptions optionsFire = new FirefoxOptions();
                    optionsFire.setCapability("browserName", "firefox");
                    optionsFire.setCapability("platformName", "WINDOWS");
                    optionsFire.addArguments("--headless");
                    driver = new FirefoxDriver(optionsFire);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid browser name: " + BROWSER);
            }

            DriverFactory.setDriver(driver);
        }
    }
    @After("@steps")
    public void fechar(Scenario ct)  {
        System.out.println("[FIM DO CENÁRIO]* " + ct.getName() + " THREAD* " + Thread.currentThread().getId());
        LocalDate currentDate = LocalDate.now();

        // Formatar a data e a hora de acordo com o padrão desejado
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String date = currentDate.format(dateFormatter);
        String time = LocalTime.now().format(timeFormatter);
        String result = ct.isFailed() ? "FAILED" : "PASSED";

        String cor = result.equals("PASSED")? GREEN_TEXT: PURPLE_TEXT;
        System.out.println("Line: " + ct.getLine());
        System.out.println(GREEN_TEXT+"+--------------------------------------------------+\n" +
                "|                " + ""+ct.getName()+"" +"                 |\n" +
                "+--------------------------------------------------+\n" +
                "|   CENARIO: " +ct.getName()+"                     "+"|\n" +
                "|   THREAD:                               "+Thread.currentThread().getId()+"       "+"|\n" +
                "|   STATUS:                               "+result+"   |\n" +
                "|   Execution Time:                     "+ time + "   |\n" +
                "+--------------------------------------------------+\n"+""+ RESET_TEXT);
        closeDriver();
    }



    @AfterStep
    public void screenshot(Scenario scenario){
        final byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);

    scenario.attach(screenshot,"image/png",scenario.getName());
    }



}
