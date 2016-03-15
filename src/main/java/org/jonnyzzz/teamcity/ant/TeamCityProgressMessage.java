package org.jonnyzzz.teamcity.ant;

import org.apache.tools.ant.BuildException;

public class TeamCityProgressMessage extends TeamCityServiceMessageBase {
    private String myValue;

    public String getMessage() {
        return myValue;
    }

    public void addText(String text) {
        myValue = text;
    }

    public void setMessage(String myValue) {
        this.myValue = myValue;
    }

    @Override
    public void execute() throws BuildException {
        logSimpleServiceMessage("progressMessage", myValue == null ? "" : myValue);
    }
}
