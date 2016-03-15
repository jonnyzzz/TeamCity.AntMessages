package org.jonnyzzz.teamcity.ant;

import org.apache.tools.ant.BuildException;

public class TeamCityBuildNumberMessage extends TeamCityServiceMessageBase {
    private String myValue;

    public String getNumber() {
        return myValue;
    }

    public void setNumber(String myValue) {
        this.myValue = myValue;
    }

    @Override
    public void execute() throws BuildException {
        if (myValue == null || myValue.trim().length() == 0) {
            throw new BuildException("'number' must be specified");
        }

        logSimpleServiceMessage("buildNumber", myValue);
    }
}
