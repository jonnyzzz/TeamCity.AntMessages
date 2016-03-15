package org.jonnyzzz.teamcity.ant;

import org.apache.tools.ant.BuildException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class TeamCityServiceMessageFlowBase extends TeamCityServiceMessageBase {
    @Nullable
    private String myFlowId = null;

    @Nullable
    public String getFlowId() {
        return myFlowId;
    }

    public void setFlowId(@Nullable String flowId) {
        myFlowId = flowId;
    }


    @Override
    protected void logSimpleServiceMessage(@NotNull String name, @NotNull String value) {
        if (myFlowId != null) {
            throw new BuildException("flowId attribute is not supported for value only service message");
        }

        super.logSimpleServiceMessage(name, value);
    }

    @Override
    protected void logComplexServiceMessage(@NotNull String name, @NotNull Map<String, String> values) {
        final Map<String, String> paramz = new LinkedHashMap<String, String>();
        if (myFlowId != null && myFlowId.length() > 0) {
            paramz.put("flowId", myFlowId);
        }
        paramz.putAll(values);

        super.logComplexServiceMessage(name, paramz);
    }
}
