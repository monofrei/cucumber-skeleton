package org.testmonkeys.cucumber.appender;

import ch.qos.logback.core.AppenderBase;

public class CukeAppender extends AppenderBase {

    @Override
    protected void append(Object o) {
        CukeScenarioContext.getInstance().getScenario().write(o.toString());
    }
}
