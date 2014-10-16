<h2> RTI*Web </h2>

RTI*Web is a purpose build application to query RTI4T events in real time as they arrive in Greenplum. This tool could be used
for other databases as it's uses generic JDBC code BUT has only been tested with Greenplum. OOTB it has reports around handset usage,
operating system usage, dropped calls, interface counts, 2g/3g/4g traffic etc. It is NOT a graphical interface, it is meant
to demonstrate querying data in real time as it hits Greenplum to show how quickly we can query RTI events.

In order to query data in real time Greenplum needs to have partitions setup which host the most recent data. It's best to have
around 1 - 2 hours of data to provide meaningful reports with. Queries should execute in under 2 seconds regardless of how
many events RTI has written to the transactions table

You can download WAR file already packaged as follows BUT it needs the config files updated to connect to Greenplum, plus SQL 
query-beans.xml needs updating

<a href="https://dl.dropboxusercontent.com/u/15829935/fe-demos/RTIWeb/rtiwebapp.war">rtiwebapp.war</a>

<h2> How to configure </h2>

- Edit resources/application-context.xml to target the correct Greenplum schema

- Edit resources/query-beans.xml to define SQL queries to run, you need to ensure the SQL in there is tested prior to adding it
RTI*Web will automatically add SQL defined in here to the web ui without having to alter the code.

- Ensure RTI events written as CSV are loaded into Greenplum table as follows, as RTI*Web drives the OOTB queries from a table
with structure as follows. the table can be whatever table name you like to use, simple edit resources/query-beans.xml to
use the correct table name here. Your table should have real time partitions setup to ensure you can query it quickly.

```
CREATE TABLE rtitrans
(
  imsi character varying(82),
  subscriber_mccmnc character varying(10),
  msisdn character varying(82),
  imei character varying(50),
  called_digits text,
  start_datetime integer,
  end_datetime integer,
  first_cell_lac integer,
  first_cell_idsac integer,
  current_cell_lac integer,
  current_cell_idsac integer,
  dr_type integer,
  status text,
  ingest_time bigint,
  processed_time bigint,
  export_time bigint,
  extra_col text,
  gploaded_time timestamp without time zone
)
WITH (APPENDONLY=true, COMPRESSTYPE=quicklz, 
  OIDS=FALSE
)
DISTRIBUTED BY (imsi);
```

- package as WAR

- deploy to tc Server or tomcat and invoke as follows if your WAR file is called rtiwebapp.war

```
http://{server-ip-address}:{server-port}/rtiwebapp/
```

![alt tag](https://dl.dropboxusercontent.com/u/15829935/fe-demos/RTIWeb/welcome.png)


Created by Pas Apicella - <a href="mailto:papicella@pivotal.io">papicella@pivotal.io</a> for Pivotal RTI real Time Reporting
