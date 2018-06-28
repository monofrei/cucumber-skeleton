package org.testmonkeys.cucumber.runner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleSteps {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Given("^this is a sample given step$")
    public void sampleGiven() throws InterruptedException {
//        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(true);
        logger.info("given");
    }

    @When("^this is a sample when step$")
    public void samplewhen() throws InterruptedException {
//        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(true);
        logger.info("when");
    }

    @Then("^this is a sample then step$")
    public void samplethen() throws InterruptedException {
//        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(true);
        logger.info("when");
    }

    @Given("^this is a sample '(.*)' step$")
    public void thisIsASampleColumnStep(String value) throws InterruptedException {
//        TimeUnit.SECONDS.sleep(2);
        logger.info(value);
    }
}
