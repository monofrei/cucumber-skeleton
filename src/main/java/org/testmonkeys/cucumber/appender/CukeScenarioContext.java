package org.testmonkeys.cucumber.appender;

import cucumber.api.Scenario;

public class CukeScenarioContext {

    private static CukeScenarioContext instance;
    private Scenario scenario;

    private CukeScenarioContext() {

    }

    public static synchronized CukeScenarioContext getInstance() {
        if (instance == null)
            instance = new CukeScenarioContext();

        return instance;
    }

    public Scenario getScenario() {
        return this.scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public void attachScreenshot(byte[] screenshot) {
        this.scenario.embed(screenshot, "image/png");
    }
}
