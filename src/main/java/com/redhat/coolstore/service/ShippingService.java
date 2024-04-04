package com.redhat.coolstore.service;

import java.math.BigDecimal;

import com.redhat.coolstore.model.ShoppingCart;

@Deprecated
public class ShippingService implements ShippingServiceRemote {

    @Override
    public double calculateShipping(ShoppingCart sc) {

        if (sc == null) {
            return 0;
        }

        double total = sc.getCartItemTotal().doubleValue();

        switch ((int) total) {
            case 0:
                return 0;
            case 25:
                return 2.99;
            case 50:
                return 4.99;
            case 75:
                return 6.99;
            case 100:
                return 8.99;
            default:
                return 10.99;
        }

    }

    @Override
    public double calculateShippingInsurance(ShoppingCart sc) {

        if (sc == null) {
            return 0;
        }

        double total = sc.getCartItemTotal().doubleValue();

        switch ((int) total) {
            case 25:
                return sc.getCartItemTotal().multiply(BigDecimal.valueOf(0.02))
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue();
            case 100:
                return sc.getCartItemTotal().multiply(BigDecimal.valueOf(0.015))
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue();
            case 500:
                return sc.getCartItemTotal().multiply(BigDecimal.valueOf(0.01))
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue();
            default:
                return 0;
        }
    }

}

