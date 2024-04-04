<s>[INST] <<SYS>>
You are an AI Assistant trained on migrating enterprise JavaEE code to Quarkus.
<</SYS>>

I will give you a JavaEE file for which I want to take one step towards migrating to Quarkus.

I will provide you with static source code analysis information highlighting an issue which needs to be addressed.

I will also provide you with an example of how a similar issue was solved in the past via a solved example.

You can refer to the solved example for a pattern of how to update the input Java EE file to Quarkus.

Fix only the problem described. Other problems will be solved in subsequent steps so it is unnecessary to handle them now.

Before attempting to migrate the code to Quarkus reason through what changes are required and why.

Pay attention to changes you make and impacts to external dependencies in the pom.xml as well as changes to imports we need to consider.

As you make changes that impact the pom.xml or imports, be sure you explain what needs to be updated.

After you have shared your step by step thinking, provide a full output of the updated file.
# Input Information

## Input File

File name: "src/main/resources/META-INF/persistence.xml"
Source file contents:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.1"
             xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="
        http://xmlns.jcp.org/xml/ns/persistence
        http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd">
    <persistence-unit name="primary">
        <jta-data-source>java:jboss/datasources/CoolstoreDS</jta-data-source>
        <properties>
            <property name="javax.persistence.schema-generation.database.action" value="none"/>
            <property name="hibernate.show_sql" value="false" />
            <property name="hibernate.format_sql" value="true" />
            <property name="hibernate.use_sql_comments" value="true" />
            <property name="hibernate.jdbc.use_get_generated_keys" value="false" />
        </properties>
    </persistence-unit>
</persistence>
```

## Issues

### Issue 1
Issue to fix: "Replace `http://xmlns.jcp.org/xml/ns/persistence` with `https://jakarta.ee/xml/ns/persistence` and change the schema version number "
Line number: 3
Solved example:
```diff

```
### Issue 2
Issue to fix: "Replace `http://xmlns.jcp.org/xml/ns/persistence` with `https://jakarta.ee/xml/ns/persistence` and change the schema version number "
Line number: 5
Solved example:
```diff

```
### Issue 3
Issue to fix: "Replace `http://xmlns.jcp.org/xml/ns/persistence` with `https://jakarta.ee/xml/ns/persistence` and change the schema version number "
Line number: 6
Solved example:
```diff

```
### Issue 4
Issue to fix: "Replace `persistence_2_1.xsd` with `persistence_3_0.xsd` and update the version attribute to `"3.0"`"
Line number: 6
Solved example:
```diff

```
### Issue 5
Issue to fix: "In the root tag, replace the `version` attribute value `2.1` with `3.0`"
Line number: 2
Solved example:
```diff

```
### Issue 6
Issue to fix: "In the root tag, replace the `version` attribute value `2.1` with `3.0`"
Line number: 8
### Issue 7
Issue to fix: "Rename properties prefixed by `javax` with `jakarta` "
Line number: 10
### Issue 8
Issue to fix: "It is recommended to move persistence related configuration from an XML file to a properties one.
 This allows centralization of the configuration in Quarkus. Check the link for more information.
 
 
 Datasource and persistence configurations in XML can be substituted with a single centralized properties file. Here is an example of a translation:
 
 The following datasource configuration:
 ```
 <datasources xmlns="http://www.jboss.org/ironjacamar/schema"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="http://www.jboss.org/ironjacamar/schema http://docs.jboss.org/ironjacamar/schema/datasources_1_0.xsd">
 <!-- The datasource is bound into JNDI at this location. We reference
 this in META-INF/persistence.xml -->
 <datasource jndi-name="java:jboss/datasources/TasksJsfQuickstartDS"
 pool-name="tasks-jsf-quickstart" enabled="true"
 use-java-context="true">
 <connection-url>jdbc:h2:mem:tasks-jsf-quickstart;DB_CLOSE_ON_EXIT=FALSE;DB_CLOSE_DELAY=-1</connection-url>
 <driver>h2</driver>
 <security>
 <user-name>sa</user-name>
 <password>sa</password>
 </security>
 </datasource>
 </datasources>
 ```
 along with the following persistence configuration:
 ```
 <persistence version="2.1"
 xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="
 http://xmlns.jcp.org/xml/ns/persistence
 http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd">
 <persistence-unit name="primary">
 <!-- We use a different datasource for tests, so as to not overwrite
 production data. This is an unmanaged data source, backed by H2, an in memory
 database. Production applications should use a managed datasource. -->
 <!-- The datasource is deployed as WEB-INF/test-ds.xml,
 you can find it in the source at src/test/resources/test-ds.xml -->
 <jta-data-source>java:jboss/datasources/TasksJsfQuickstartDS</jta-data-source>
 <properties>
 <!-- Properties for Hibernate -->
 <property name="hibernate.hbm2ddl.auto" value="create-drop" />
 <property name="hibernate.show_sql" value="false" />
 </properties>
 </persistence-unit>
 </persistence>
 ```
 can be translated to:
 ```
 quarkus.datasource.jdbc.url=jdbc:h2:mem:tasks-jsf-quickstart;DB_CLOSE_ON_EXIT=FALSE;DB_CLOSE_DELAY=-1
 quarkus.datasource.db-kind=h2
 quarkus.datasource.username=sa
 quarkus.datasource.password=sa

 quarkus.hibernate-orm.database.generation=drop-and-create
 ```"
Line number: 
Solved example:
```diff

```
# Output Instructions

Structure your output in Markdown format such as:

## Reasoning

Write the step by step reasoning in this markdown section. If you are unsure of a step or reasoning, clearly state you are unsure and why.

## Updated File

```xml
// Write the updated file for Quarkus in this section
```

## Additional Information (optional)

If you have any additonal details or steps that need to be performed, put it here

[/INST]

