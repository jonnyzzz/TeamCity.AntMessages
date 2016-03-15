package org.jonnyzzz.teamcity.ant;

import org.apache.tools.ant.BuildException;

public class TeamCityProblemMessage extends TeamCityServiceMessageFlowBase {
    private String identity;
    private String description;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void execute() throws BuildException {
        if (identity == null || identity.trim().length() == 0) {
            throw new BuildException("'identity' must be defined");
        }

        if (description == null || description.trim().length() == 0) {
            throw new BuildException("'description' must be defined");
        }

        logComplexServiceMessage("buildProblem", map("identity", identity, "description", description));
    }
}
