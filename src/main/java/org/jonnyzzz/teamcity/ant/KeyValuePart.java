package org.jonnyzzz.teamcity.ant;

import org.jetbrains.annotations.Nullable;

/**
 * @author Eugene Petrenko (eugene.petrenko@gmail.com)
 *
 */
public class KeyValuePart {
  private String myName;
  private String myValue;

  @Nullable
  public String getName() {
    return myName;
  }

  public void setName(String name) {
    myName = name;
  }

  @Nullable
  public String getValue() {
    return myValue;
  }

  public void setValue(String value) {
    myValue = value;
  }
}
