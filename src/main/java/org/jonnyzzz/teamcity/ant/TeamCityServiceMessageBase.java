package org.jonnyzzz.teamcity.ant;

import jetbrains.buildServer.messages.serviceMessages.ServiceMessage;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class TeamCityServiceMessageBase extends Task {
    private void logServiceMessage(@NotNull final String message) {
        final String NL = "\r\n";
        final String text = NL + message + NL;
        //TODO: may use socket or console output to make sure it's not skipped
        getProject().log(this, text, Project.MSG_WARN);
    }

    protected void logSimpleServiceMessage(@NotNull final String name,
                                           @NotNull final String value) {
        logServiceMessage(ServiceMessage.asString(name, value));
    }

    protected void logComplexServiceMessage(@NotNull final String name,
                                            @NotNull final Map<String, String> values) {
        final Map<String, String> actual = new LinkedHashMap<String, String>();
        actual.putAll(values);

        logServiceMessage(ServiceMessage.asString(name, actual));
    }

    @NotNull
    protected final Map<String, String> map(@NotNull final String key1,
                                            @NotNull final String value1) {
        final Map<String, String> map = new LinkedHashMap<String, String>();
        map.put(key1, value1);
        return map;
    }

    @NotNull
    protected final Map<String, String> map(@NotNull final String key1,
                                            @NotNull final String value1,
                                            @NotNull final String key2,
                                            @NotNull final String value2) {
        final Map<String, String> map = new LinkedHashMap<String, String>();
        map.put(key1, value1);
        map.put(key2, value2);
        return map;
    }
}
