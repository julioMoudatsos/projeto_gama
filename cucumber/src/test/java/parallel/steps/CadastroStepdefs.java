package parallel.steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static parallel.config.DriverFactory.getDriver;

public class CadastroStepdefs {






    @Dado("que estou na página inicial do  BING")
    public void queEstouNaPáginaInicialDoBING() {
    }

    @Quando("eu pesquiso no BING por {string}")
    public void euPesquisoNoBINGPor(String pesquisa) {
        getDriver().get("https://www.bing.com/?cc=br");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(40));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("sb_form_q")));
        getDriver().findElement(By.id("sb_form_q")).sendKeys(pesquisa);
    }

    @Então("eu vejo os resultados da pesquisa no  BING")
    public void euVejoOsResultadosDaPesquisaNoBING() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(40));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("search_icon")));

        getDriver().findElement(By.id("search_icon")).click();
    }
}
