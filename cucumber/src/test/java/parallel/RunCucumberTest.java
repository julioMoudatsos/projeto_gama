package parallel;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/parallel",
        glue = "parallel",
        plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","json:target/cucumber-report/cucumber.json"})

public class RunCucumberTest {
}