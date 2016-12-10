# How to config PostgreSql driver in WildFly

This tutor will help you to config a driver on your wildfly server. All commands are for unix platform, if you are using windows, you can change the `*.sh files` with `*.bat files`.

# Requirements
- JDK
- PostgreSql
- WildFly Server
- PostgreSql JDBC

# Configuring

First, go to your WildFly folder, go into bin folder. Execute `add-user.sh` to add a user if you haven’t already done. If you have any doubt, you can get help with this [link]( https://docs.jboss.org/author/display/WFLY8/add-user+utility?_sscc=t)

After execute the `standalone.sh` to start the server. For the next step run the `jboss-cli.sh`,  and then type connect to connect the server.

![jboss-cli.sh](images/jboss-cli-sh.png)

Now, to add postgres module in wildfly, download postgresql [driver](https://github.com/CodeShareEducation/java-service-order/raw/master/config/postgresql-9.4-1206-jdbc41.jar) in one folder of your choice, and run this command, `module add --name=org.postgres --resources=/tmp/postgresql-9.4-1206-jdbc41.jar --dependencies=javax.api,javax.transaction.api`.

Then we have to install the driver in the wildfly. `/subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgres",driver-class-name=org.postgresql.Driver)`.
