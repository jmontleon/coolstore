package com.redhat.coolstore.service;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.redhat.coolstore.model.Product;
import com.redhat.coolstore.model.ShoppingCart;
import com.redhat.coolstore.model.ShoppingCartItem;
import com.redhat.coolstore.service.PromoService;
import com.redhat.coolstore.service.ShoppingCartOrderProcessor;

@Dependent
public class ShoppingCartService  {

    @Inject
    Logger log;

    @Inject
    ProductService productServices;

    @Inject
    PromoService ps;

    @Inject
    ShoppingCartOrderProcessor shoppingCartOrderProcessor;

    @Inject
    ShoppingCart shoppingCart;

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public ShoppingCart checkOutShoppingCart() {
        ShoppingCart cart = this.getShoppingCart();

        log.info("Sending  order: ");
        shoppingCartOrderProcessor.process(cart);

        cart.resetShoppingCartItemList();
        priceShoppingCart(cart);
        return cart;
    }

    public void priceShoppingCart(ShoppingCart sc) {

        if (sc != null) {

            initShoppingCartForPricing(sc);

            if (sc.getShoppingCartItemList() != null && sc.getShoppingCartItemList().size() > 0) {

                ps.applyCartItemPromotions(sc);

                for (ShoppingCartItem sci : sc.getShoppingCartItemList()) {

                    sc.setCartItemPromoSavings(
                            sc.getCartItemPromoSavings() + sci.getPromoSavings() * sci.getQuantity());
                    sc.setCartItemTotal(sc.getCartItemTotal() + sci.getPrice() * sci.getQuantity());

                }

                sc.setShippingTotal(calculateShipping(sc));

                if (sc.getCartItemTotal() >= 25) {
                    sc.setShippingTotal(sc.getShippingTotal()
                            + calculateShippingInsurance(sc));
                }

            }

            ps.applyShippingPromotions(sc);

            sc.setCartTotal(sc.getCartItemTotal() + sc.getShippingTotal());

        }

    }

    private void initShoppingCartForPricing(ShoppingCart sc) {

        sc.setCartItemTotal(0);
        sc.setCartItemPromoSavings(0);
        sc.setShippingTotal(0);
        sc.setShippingPromoSavings(0);
        sc.setCartTotal(0);

        for (ShoppingCartItem sci : sc.getShoppingCartItemList()) {
            Product p = getProduct(sci.getProduct().getItemId());
            //if product exist
            if (p != null) {
                sci.setProduct(p);
                sci.setPrice(p.getPrice());
            }

            sci.setPromoSavings(0);
        }

    }

    public Product getProduct(String itemId) {
        return productServices.getProductByItemId(itemId);
    }

    public double calculateShipping(ShoppingCart sc) {
        // Implement the calculation of shipping cost
        return 0;
    }

    public double calculateShippingInsurance(ShoppingCart sc) {
        // Implement the calculation of shipping insurance cost
        return 0;
    }
}
