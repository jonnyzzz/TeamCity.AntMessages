package org.jonnyzzz.teamcity.ant;

import jetbrains.buildServer.messages.serviceMessages.ServiceMessage;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Eugene Petrenko (eugene.petrenko@gmail.com)
 *
 */
public class TeamCityServiceMessageTask extends Task {
  @Nullable private String myFlowId = null;
  @Nullable private String myName = null;
  @Nullable private String myValue = null;
  private final List<KeyValuePart> myArguments = new ArrayList<KeyValuePart>();

  public void setName(@Nullable String name) {
    myName = name;
  }

  public void setValue(@Nullable String value) {
    myValue = value;
  }

  @Nullable
  public String getFlowId() {
    return myFlowId;
  }

  public void setFlowId(@Nullable String flowId) {
    myFlowId = flowId;
  }

  @NotNull
  public KeyValuePart createParam() {
    final KeyValuePart part = new KeyValuePart();
    myArguments.add(part);
    return part;
  }

  @Override
  public void execute() throws BuildException {
    if (myName == null || myName.trim().isEmpty()) {
      throw new BuildException("'name' attribute should be defined");
    }

    if (myValue != null) {
      if (!myArguments.isEmpty()) {
        throw new BuildException("'value' attribute must not co-exist with 'param' sub-elements");
      }

      if (myFlowId != null) {
        throw new BuildException("flowId attribute is not supported for value only service message");
      }

      logServiceMessage(ServiceMessage.asString(myName, myValue));
    } else {

      final Map<String, String> paramz = new LinkedHashMap<String, String>();
      
      if (myFlowId != null && myFlowId.length() > 0) {
        paramz.put("flowId", myFlowId);
      }

      for (KeyValuePart arg : myArguments) {
        final String name = arg.getName();
        final String value = arg.getValue();

        if (name == null || name.trim().length() == 0) {
          throw new BuildException("Some 'param' element has no 'name' attribute");
        }

        if (arg.getSkipIfEmpty() && (value == null || value.length() == 0)) {
          continue;
        }

        if (value == null) {
          throw new BuildException("Some 'param' element has no 'value' attribute");
        }

        final String oldValue = paramz.put(name, value);
        if (oldValue != null) {
          throw new BuildException("Multiple values for <param name='" + name + "' ...");
        }
      }

      logServiceMessage(ServiceMessage.asString(myName, paramz));
    }
  }

  private void logServiceMessage(@NotNull final String message) {
    final String NL = "\r\n";
    final String text = NL + message + NL;
    //TODO: may use socket or console output to make sure it's not skipped
    getProject().log(this, text, Project.MSG_WARN);
  }

}

