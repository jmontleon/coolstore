<s>[INST] <<SYS>>
You are an AI Assistant trained on migrating enterprise JavaEE code to Quarkus.
<</SYS>>

I will give you a JavaEE file for which I want to take one step towards migrating to Quarkus.

Fix only the problem described. Other problems will be solved in subsequent steps so it is unnecessary to handle them now.

Before attempting to migrate the code to Quarkus reason through what changes are required and why.

Pay attention to changes you make and impacts to external dependencies in the pom.xml as well as changes to imports we need to consider.

As you make changes that impact the pom.xml or imports, be sure you explain what needs to be updated.

After you have shared your step by step thinking, provide a full output of the updated file.
# Input Information

## Input File

File name: "src/main/webapp/WEB-INF/web.xml"
Source file contents:
```xml
<!--suppress ServletWithoutMappingInspection -->
<web-app xmlns="http://java.sun.com/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
         version="3.0">
    <distributable />
</web-app>

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


---
<s>[INST] <<SYS>>
You are an AI Assistant trained on migrating enterprise JavaEE code to Quarkus.
<</SYS>>

I will give you a JavaEE file for which I want to take one step towards migrating to Quarkus.

Fix only the problem described. Other problems will be solved in subsequent steps so it is unnecessary to handle them now.

Before attempting to migrate the code to Quarkus reason through what changes are required and why.

Pay attention to changes you make and impacts to external dependencies in the pom.xml as well as changes to imports we need to consider.

As you make changes that impact the pom.xml or imports, be sure you explain what needs to be updated.

After you have shared your step by step thinking, provide a full output of the updated file.
# Input Information

## Input File

File name: "src/main/webapp/WEB-INF/web.xml"
Source file contents:
```xml
<!--suppress ServletWithoutMappingInspection -->
<web-app xmlns="http://java.sun.com/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
         version="3.0">
    <distributable />
</web-app>

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

