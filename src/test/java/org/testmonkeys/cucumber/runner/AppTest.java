package org.testmonkeys.cucumber.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Unit test for simple App.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
//        tags = {"@run"},
        glue = {"org.testmonkeys.cucumber.runner"},
        format = {"org.testmonkeys.cucumber.runner.LogsFormatter",
                "org.testmonkeys.cucumber.ext.formatters.json.PerFeatureFormatter:target/json-report",
                "html:target/cucumber-reports"}
)
public class AppTest {

}
