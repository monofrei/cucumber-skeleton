package org.testmonkeys.cucumber.runner;


import cucumber.runtime.StepDefinitionMatch;
import gherkin.formatter.Formatter;
import gherkin.formatter.Reporter;
import gherkin.formatter.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.testmonkeys.cucumber.runner.Status.*;

public class LogsFormatter implements Formatter, Reporter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Feature currentFeature;
    private String featureUri;
    private Scenario currentScenario;
    private ScenarioOutline currentScenarioOutline;
    private String currentStep;
    private Status testStatus;

    @Override
    public void syntaxError(String s, String s1, List<String> list, String s2, Integer integer) {

    }

    @Override
    public void uri(String s) {
        featureUri = s;
    }

    @Override
    public void feature(Feature feature) {
        if (currentFeature != null) {
            logger.info("FEATURE FINISHED:" + feature.getName());
        }
        logger.info("FEATURE STARTED:" + feature.getName() + "[" + featureUri + "]");
        currentFeature = feature;
    }

    @Override
    public void scenarioOutline(ScenarioOutline scenarioOutline) {
        currentScenarioOutline = scenarioOutline;
        logger.info("[EXECUTING SCENARIO OUTLINE]:" + scenarioOutline.getName());
    }

    @Override
    public void examples(Examples examples) {
        logger.info("[EXAMPLES]:" + System.lineSeparator() + displayExamples(examples.getRows()));
    }

    private StringBuilder displayExamples(List<ExamplesTableRow> examples) {
        final StringBuilder builder = new StringBuilder();
        for (ExamplesTableRow row : examples) {
            builder.append("|");
            row.getCells().forEach(c -> builder.append(c).append("|"));
            builder.append(System.lineSeparator());
        }
        return builder;
    }

    @Override
    public void startOfScenarioLifeCycle(Scenario scenario) {
        currentScenario = scenario;
        String name = scenario.getName();
        int line = scenario.getLine();
        TestLogHelper.stopTestLogging();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");

        TestLogHelper.startTestLogging(format.format(new Date().getTime()) + "_" + name + "_" + line);
        logger.info("[TEST STARTED]: " + name + " at line[" + line + "]");
        logger.info(System.lineSeparator());
    }

    @Override
    public void background(Background background) {
        logger.info("[BACKGROUND]:" + background.getName());
    }

    @Override
    public void scenario(Scenario scenario) {

    }

    @Override
    public void step(Step step) {

    }

    @Override
    public void endOfScenarioLifeCycle(Scenario scenario) {
        currentStep = null;
        currentScenario = null;
        logger.info("[TEST " + testStatus + "]:" + scenario.getName());
        //TODO add test duration
    }

    @Override
    public void done() {

    }

    @Override
    public void close() {

    }

    @Override
    public void eof() {

    }

    @Override
    public void before(Match match, Result result) {
        Status status = Status.valueOf(result.getStatus().toUpperCase());
        if (status.equals(FAILED))
            logger.error("[BEFORE HOOK FAILED]:" + match.getLocation(), result.getError());
        else
            logger.info("[BEFORE HOOK " + status + "]:" + match.getLocation());
    }

    @Override
    public void result(Result result) {
        Status status = Status.valueOf(result.getStatus().toUpperCase());
        if (status.equals(FAILED))
            logger.error("[STEP FAILED]:" + currentStep, result.getError());
        else
            logger.info("[STEP " + result.getStatus().toUpperCase() + "]:" + currentStep);

        applyTestStatus(Status.valueOf(result.getStatus().toUpperCase()));
        //TODO log error in case fail
        //TODO add duration

    }

    private void applyTestStatus(Status newStatus) {
        if (Objects.equals(testStatus, FAILED)) return;
        if (Objects.equals(testStatus, PASSED) && newStatus.equals(SKIPPED)) return;
        testStatus = newStatus;
    }

    @Override
    public void after(Match match, Result result) {
        Status status = Status.valueOf(result.getStatus().toUpperCase());
        if (status.equals(FAILED))
            logger.error("[AFTER HOOK FAILED]:" + match.getLocation(), result.getError());
        else
            logger.info("[AFTER HOOK " + status + "]:" + match.getLocation());
    }

    @Override
    public void match(Match match) {
        if (match instanceof StepDefinitionMatch) {
            currentStep = ((StepDefinitionMatch) match).getStepLocation().getMethodName();
            logger.info("[STEP STARTED]:" + currentStep);
        }
    }

    @Override
    public void embedding(String mimeType, byte[] data) {

    }

    @Override
    public void write(String text) {

    }
}
