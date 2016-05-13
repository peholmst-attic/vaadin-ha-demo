Vaadin HA Demo
==============

This is a small Vaadin and Java EE 7 / Java 8 application that should be deployable in a Wildfly 9 HA cluster. 
When session replication is enabled, failover between nodes should work without the users noticing anything.

In order to try this out, you will need:

- Java 8
- A Wildfly 9 cluster configured for HA
- A datasource with the JNDI name 'java:/MySqlDS' (you can change it in `persistence.xml` if you want to).
- Maven

The DDL for the database in MySQL is:

```
CREATE TABLE myentity (
  id bigint not null auto_increment, 
  version bigint not null, 
  name varchar(200), 
  primary key(id)
) ENGINE=InnoDB;
```

Once the environment is set up and configured, you can package the application by running `mvn package` and then 
deploy the WAR file. The URL for the UI will be `http://yourserver:yourport/contextpath/demo`.
