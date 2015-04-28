TeamCity.AntMessages
====================

This is a repackage of serviceMessages.jar from TeamCity open-api
as Ant task.

https://confluence.jetbrains.com/display/TCD9/Build+Script+Interaction+with+TeamCity#BuildScriptInteractionwithTeamCity-ServiceMessages


Usage in Ant
```
    <!-- import task into Ant -->
    <taskdef name="teamcity-service-message"
             classname="org.jonnyzzz.teamcity.ant.TeamCityServiceMessageTask">
        <classpath>
            <fileset dir="PATH_TO_LIBRARIES" includes="TeamCity.AntMessages-VERSION-release.jar"/>
        </classpath>
    </taskdef>


    <!-- post simple service message
    <teamcity-service-message name="name" value="value"/>

    <!-- post complex service message -->
    <teamcity-service-message name="name">
        <param name="aaa" value="vvv"/>
        <param name="aa2a" value="v2vv"/>
    </teamcity-service-message>
```

Building
--------

Run ```./gradlew dist```

License
-------

MIT. See LICENSE


Notice
------

See NOTICE

