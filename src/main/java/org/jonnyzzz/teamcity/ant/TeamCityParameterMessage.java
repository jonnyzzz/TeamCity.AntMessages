package org.jonnyzzz.teamcity.ant;

import org.apache.tools.ant.BuildException;

public class TeamCityParameterMessage extends TeamCityServiceMessageFlowBase {
    private String myPrefix;
    private String myName;
    private String myValue;

    public String getPrefix() {
        return myPrefix;
    }

    public void setPrefix(String prefix) {
        this.myPrefix = prefix;
    }

    public String getName() {
        return myName;
    }

    public void setName(String myKey) {
        this.myName = myKey;
    }

    public String getValue() {
        return myValue;
    }

    public void setValue(String myValue) {
        this.myValue = myValue;
    }

    @Override
    public void execute() throws BuildException {
        if (myName == null || myName.trim().length() == 0) {
            throw new BuildException("'name' must be specified");
        }
        if (myValue == null || myValue.trim().length() == 0) {
            throw new BuildException("'value' must be specified");
        }

        logComplexServiceMessage("setParameter", map("name", (myPrefix == null ? "" : myPrefix) + myName, "value", myValue));
    }
}
