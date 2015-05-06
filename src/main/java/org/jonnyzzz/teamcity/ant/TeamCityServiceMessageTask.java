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
public class TeamCityServiceMessageTask extends TeamCityServiceMessageFlowBase {
  @Nullable private String myName = null;
  @Nullable private String myValue = null;
  private final List<KeyValuePart> myArguments = new ArrayList<KeyValuePart>();

  public void setName(@Nullable String name) {
    myName = name;
  }

  public void setValue(@Nullable String value) {
    myValue = value;
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

      logSimpleServiceMessage(myName, myValue);
    } else {

      final Map<String, String> paramz = new LinkedHashMap<String, String>();

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

      logComplexServiceMessage(myName, paramz);
    }
  }

}

