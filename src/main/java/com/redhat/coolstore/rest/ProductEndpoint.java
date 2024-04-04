package com.redhat.coolstore.rest;

import java.util.List;

import com.redhat.coolstore.model.Product;
import com.redhat.coolstore.service.ProductService;
import io.quarkus.arc.inject.Inject;
import org.jboss.resteasy.reactive.common.core.MediaType;

@ApplicationScoped
@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductEndpoint {

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

