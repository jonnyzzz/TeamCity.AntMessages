package org.jonnyzzz.teamcity.ant;

import org.apache.tools.ant.BuildException;

public class TeamCityStatisticsMessage extends TeamCityServiceMessageFlowBase {
    private String myKey;
    private String myValue;

    public String getKey() {
        return myKey;
    }

    public void setKey(String myKey) {
        this.myKey = myKey;
    }

    public String getValue() {
        return myValue;
    }

    public void setValue(String myValue) {
        this.myValue = myValue;
    }

    @Override
    public void execute() throws BuildException {
        if (myKey == null || myKey.trim().length() == 0) {
            throw new BuildException("'key' must be specified");
        }
        if (myValue == null || myValue.trim().length() == 0) {
            throw new BuildException("'value' must be specified");
        }

        logComplexServiceMessage("buildStatisticValue", map("key", myKey, "value", myValue));
    }
}
