<h2> RTI*Web </h2>

RTI*Web is a purpose build application to query RTI4T events in real time as thier hit Greenplum. This tool could be used
for other databases as it's uses generic JDBC code BUT has only been tested with Greenplum. OOTB it has reports around handset usage,
operating system usage, dropped calls, interface counts, 2g/3g/4g traffic etc. It is NOT a graphical interface, it is meant
to demonstrate querying data in real time as it hits greenplum to show how quickly we can query RTI events.

In order toq uery data in real time Greenplum needs to have partitions setup which host the most recent data. It's best to have
around 1 - 2 hours of data to provide meaningful reports with. Queries should execute in under 2 seconds regardless of how
many events RTI has written to the transactions table

<h2> How to configure </h2>

- Edit resources/application-context.xml to target the correct Greenplum schema

- Edit resources/query-beans.xml to define SQL queries to run, you need to ensure the SQL in there is tested prior to adding it
RTI*Web will automatically add SQL defined in here to the web ui without having to alter the code.

- Ensure RTI events written as CSV are loaded into Greenplum table as follows, as RTI*Web drives the OOTB queries from a table
as wity structure as follows. the table can be whatever table name you like to use, simple edit resources/query-beans.xml to
use the correct table name here.

```

```

- package as WAR

- deploy to tc Server or tomcat and invoke as follows if you

```
http://{server-ip-address}:{server-port}/{warfilename}/
```

Created by Pas Apicella - <a href="mailto:papicella@pivotal.io">papicella@pivotal.io</a> for Pivotal RTI real Time Reporting
