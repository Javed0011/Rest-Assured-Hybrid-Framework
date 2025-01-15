package runners;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features", 
    glue = "stepDefinitions",           
    plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class TestRunner {
}