package parallel.steps;


import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


import static parallel.config.DriverFactory.getDriver;

public class LoginStepdefs {


    @Dado("que estou na página inicial do Google")
    public void queEstouNaPáginaInicialDoGoogle() {
    }

    @Quando("eu pesquiso por {string}")
    public void euPesquisoPor(String pesquisa) {
        getDriver().get("https://www.google.com");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(40));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//textarea[@class='gLFyf']")));
        getDriver().findElement(By.xpath("//textarea[@class='gLFyf']")).sendKeys(pesquisa);

    }

    @Então("eu vejo os resultados da pesquisa")
    public void euVejoOsResultadosDaPesquisa() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(By.className("gNO89b")));
        getDriver().findElement(By.className("gNO89b")).click();
    }
}
