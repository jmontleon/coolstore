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

File name: "src/main/java/com/redhat/coolstore/rest/ProductEndpoint.java"
Source file contents:
```java
package com.redhat.coolstore.rest;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.redhat.coolstore.model.Product;
import com.redhat.coolstore.service.ProductService;

@RequestScoped
@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductEndpoint implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7227732980791688773L;

    @Inject
    private ProductService pm;


    @GET
    @Path("/")
    public List<Product> listAll() {
        return pm.getProducts();
    }

    @GET
    @Path("/{itemId}")
    public Product getProduct(@PathParam("itemId") String itemId) {
        return pm.getProductByItemId(itemId);
    }

}

```

# Output Instructions

Structure your output in Markdown format such as:

## Reasoning

Write the step by step reasoning in this markdown section. If you are unsure of a step or reasoning, clearly state you are unsure and why.

## Updated File

```java
// Write the updated file for Quarkus in this section
```

## Additional Information (optional)

If you have any additonal details or steps that need to be performed, put it here

[/INST]

