package org.testmonkeys.cucumber.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class CukeAppenderFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent iLoggingEvent) {
        return iLoggingEvent.getLoggerName().contains("LogsFormatter") ? FilterReply.DENY : FilterReply.ACCEPT;
    }
}
