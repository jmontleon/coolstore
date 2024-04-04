package com.redhat.coolstore.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import jakarta.ejb.Remote; // Changed import statement
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

import com.redhat.coolstore.model.ShoppingCart;

@ApplicationScoped // Changed annotation
public class ShippingService implements ShippingServiceRemote {

    @Override
    @GET
    @Path("/calculate-shipping") // Added endpoint path
    public Response calculateShipping(ShoppingCart sc) {

        if (sc != null) {

            if (sc.getCartItemTotal() >= 0 && sc.getCartItemTotal() < 25) {

                return Response.ok(2.99).build();

            } else if (sc.getCartItemTotal() >= 25 && sc.getCartItemTotal() < 50) {

                return Response.ok(4.99).build();

            } else if (sc.getCartItemTotal() >= 50 && sc.getCartItemTotal() < 75) {

                return Response.ok(6.99).build();

            } else if (sc.getCartItemTotal() >= 75 && sc.getCartItemTotal() < 100) {

                return Response.ok(8.99).build();

            } else if (sc.getCartItemTotal() >= 100 && sc.getCartItemTotal() < 10000) {

                return Response.ok(10.99).build();

            }

        }

        return Response.ok(0).build();

    }

    @Override
    @GET
    @Path("/calculate-shipping-insurance") // Added endpoint path
    public Response calculateShippingInsurance(@QueryParam("value") double value, @QueryParam("percentOfTotal") double percentOfTotal) {

        if (value >= 25 && value < 100) {

            return Response.ok(BigDecimal.valueOf(value * percentOfTotal)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue()).build();

        } else if (value >= 100 && value < 500) {

            return Response.ok(BigDecimal.valueOf(value * percentOfTotal)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue()).build();

        } else if (value >= 500 && value < 10000) {

            return Response.ok(BigDecimal.valueOf(value * percentOfTotal)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue()).build();

        }

        return Response.ok(0).build();
    }

}

