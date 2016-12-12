# Service Order
Project to work with service orders from a cel phone store made with: Java, JSF, CDI, Hibernate, EJB, Jboss and PostgreSQL, and take care with I18N. The languages available are English and Portuguese.

## How to install

1. What do you need?
 - Jboss Wildfly 8.1.0.Final
 - PostgreSQL 9.4
 - JDK 1.7 +

2. First you have to config your environment, to do this you can follow this [instructions](how-to-config-data-source-wildfly.md)).

3. You'll need set the correct username and password of you database to the default value of application (username=postgres, password=postgres), or change them in the service-order-ds.xml file (it's into `service-order/service-order-ear/src/main/application/META-INF`).

4. Create a new database with `serviceOrder` name. You dont't have to worry about creating tables, because in first time that the application starts, it is going to create for us.

5. Import the project to your IDE (IntelliJ, Eclipse, Netbeans).

6. Config JBoss Server in the IDE.

7. Add the project to the server.

8. Start the server.

9. Open the browser and acess the adress [link](http://localhost:8080/service-order-web/)
