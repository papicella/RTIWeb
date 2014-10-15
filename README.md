<h2> RTI*Web </h2>

RTI*Web is a purpose build application to query RTI4T events in real time as thier hit Greenplum. This tool could be used
for other databases as it's uses generic JDBC code BUT has only been tested with Greenplum. OOTB it has reports around handset usage,
operating system usage, dropped calls, interface counts, 2g/3g/4g traffic etc. It is NOT a graphical interface, it is meant
to demonstarte query data in real time as it hits greenplum to show how quickly we can query RTI events.

<h2> How to configure </h2>

- Edit resources/application-context.xml to target the correct Greenplum schema

- Edit query-beans.xml to define SQL queries to run, you need to ensure the SQL in there is tested prior to adding it
RTI*Web will automatically add SQL defined in here to the web ui without having to alter the code.

- package as WAR

- deploy to tc Server or tomcat and invoke as follows if you

```
http://{server-ip-address}:{server-port}/{warfilename}/
```

Created by Pas Apicella - <a href="mailto:papicella@pivotal.io">papicella@pivotal.io</a> for Pivotal RTI real Time Reporting
