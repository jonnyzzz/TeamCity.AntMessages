package org.jonnyzzz.teamcity.ant;

import org.apache.tools.ant.BuildException;

public class TeamCityArtifactsMessage extends TeamCityServiceMessageBase {
    private String myValue;

    public String getArtifacts() {
        return myValue;
    }

    public void addText(String text) {
        myValue = text;
    }

    public void setArtifacts(String myValue) {
        this.myValue = myValue;
    }

    @Override
    public void execute() throws BuildException {
        if (myValue == null || myValue.trim().length() == 0) {
            throw new BuildException("Artifacts pattern is empty");
        }
        logSimpleServiceMessage("publishArtifacts", myValue == null ? "" : myValue);
    }
}
