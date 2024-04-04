package com.redhat.coolstore.utils;

import com.redhat.coolstore.model.CatalogItemEntity;
import com.redhat.coolstore.model.Order;
import com.redhat.coolstore.model.OrderItem;
import com.redhat.coolstore.model.Product;
import com.redhat.coolstore.model.ShoppingCart;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

/**
 * Created by tqvarnst on 2017-03-30.
 */
public class Transformers {

    private static final String[] RANDOM_NAMES = {"Sven Karlsson","Johan Andersson","Karl Svensson","Anders Johansson","Stefan Olson","Martin Ericsson"};
    private static final String[] RANDOM_EMAILS = {"sven@gmail.com","johan@gmail.com","karl@gmail.com","anders@gmail.com","stefan@gmail.com","martin@gmail.com"};

    private static Logger log = Logger.getLogger(Transformers.class.getName());

    public static Product toProduct(CatalogItemEntity entity) {
        Product prod = new Product();
        prod.setItemId(entity.getItemId());
        prod.setName(entity.getName());
        prod.setDesc(entity.getDesc());
        prod.setPrice(entity.getPrice());
        if (entity.getInventory() != null) {
            prod.setLocation(entity.getInventory().getLocation());
            prod.setLink(entity.getInventory().getLink());
            prod.setQuantity(entity.getInventory().getQuantity());
        } else {
            log.warning("Inventory for " + entity.getName() + "[" + entity.getItemId()+ "] unknown and missing");
        }
        return prod;
    }

    public static String shoppingCartToJson(ShoppingCart cart) {
        JSONArray cartItems = new JSONArray();
        cart.getShoppingCartItemList().forEach(item -> {
            cartItems.put(new JSONObject()
                .put("productSku",item.getProduct().getItemId())
                .put("quantity",item.getQuantity())
            );
        });

        int randomNameAndEmailIndex = ThreadLocalRandom.current().nextInt(RANDOM_NAMES.length);

        JSONObject jsonObject = new JSONObject()
            .put("orderValue", cart.getCartTotal())
            .put("customerName",RANDOM_NAMES[randomNameAndEmailIndex])
            .put("customerEmail",RANDOM_EMAILS[randomNameAndEmailIndex])
            .put("retailPrice", cart.getShoppingCartItemList().stream().mapToDouble(i -> i.getQuantity()*i.getPrice()).sum())
            .put("discount", cart.getCartItemPromoSavings())
            .put("shippingFee", cart.getShippingTotal())
            .put("shippingDiscount", cart.getShippingPromoSavings())
            .put("items",cartItems) 
            ;
        return jsonObject.toString();
    }

    public static Order jsonToOrder(String json) {
        JSONTokener jsonReader = new JSONTokener(json);
        JSONObject rootObject = new JSONObject(jsonReader);
        Order order = new Order();
        order.setCustomerName(rootObject.getString("customerName"));
        order.setCustomerEmail(rootObject.getString("customerEmail"));
        order.setOrderValue(rootObject.getDouble("orderValue"));
        order.setRetailPrice(rootObject.getDouble("retailPrice"));
        order.setDiscount(rootObject.getDouble("discount"));
        order.setShippingFee(rootObject.getDouble("shippingFee"));
        order.setShippingDiscount(rootObject.getDouble("shippingDiscount"));
        JSONArray jsonItems = rootObject.getJSONArray("items");
        List<OrderItem> items = new ArrayList<OrderItem>(jsonItems.length());
        for (int i = 0; i < jsonItems.length(); i++) {
            JSONObject jsonItem = jsonItems.getJSONObject(i);
            OrderItem oi = new OrderItem();
            oi.setProductId(jsonItem.getString("productSku"));
            oi.setQuantity(jsonItem.getInt("quantity"));
            items.add(oi);
        }
        order.setItemList(items); 
        return order;
    }


}
