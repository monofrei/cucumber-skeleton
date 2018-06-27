package org.testmonkeys.cucumber.runner;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testmonkeys.cucumber.appender.CukeScenarioContext;

public class Hooks {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void before(Scenario scenario) {
        CukeScenarioContext.getInstance().setScenario(scenario);
        logger.info("[BEFORE]");
    }

    @After
    public void after(Scenario scenario) {
        logger.info("[AFTER]");
    }
}
