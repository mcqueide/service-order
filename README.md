# Service Order
Project to work with service orders from a mobile phone store made with: Java, JSF, CDI, Hibernate, Rest, EJB, Jboss and PostgreSQL, and take care with I18N. The languages available are English and Portuguese. Furthermore, we have another module, service-order-angular, this module is a only front-end module that consumes rest service from service-order project. It is build with angular and bootstrap and use some tools as bower and npm.

## How to install

1. What do you need?
 - Jboss Wildfly 8.1.0.Final
 - PostgreSQL 9.4
 - JDK 1.8
 - Maven
 - NodeJS
 - Git
 - NPM
 - Bower

2. First you have to config your environment, to do this you can follow this [instructions](how-to-config-data-source-wildfly.md)).

### Back-end

3. You'll need set the correct username and password of you database to the default value of application (username=postgres, password=postgres), or change them in the service-order-ds.xml file (it's into `service-order/service-order-ear/src/main/application/META-INF`).

4. Create a new database with `serviceorder` name and execute this [script](config/script.sql).

5. Import the project to your IDE (IntelliJ, Eclipse, Netbeans).

6. Config JBoss Server in the IDE.

7. Add the project to the server.

8. Start the server.

9. Open the browser and acess the adress [link](http://localhost:8080/service-order-web/)

### Front-end (Angular)

10. Go into the service-order-angular folder, and run `bower install` command to download all the css and angular dependencies. After that, execute `npm install` to install all npm package that are necessary to the project. Finally, type `gulp watch`, a browser window will be open with the project, be sure that wildfly server is running, because this module needs to consume rest servive from the service-order project.
