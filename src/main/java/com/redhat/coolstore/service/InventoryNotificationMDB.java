package com.redhat.coolstore.service;

import com.redhat.coolstore.model.Order;
import com.redhat.coolstore.utils.Transformers;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.util.Hashtable;
import java.util.function.Consumer;

@ApplicationScoped
public class InventoryNotificationMDB {

    private static final int LOW_THRESHOLD = 50;

    @Inject
    private CatalogService catalogService;

    @ConfigProperty(name = "quarkus.jndi.factory")
    private String jndiFactory;

    @ConfigProperty(name = "quarkus.jms.topic")
    private String topic;

    @Inject
    private Logger log;

    public void onMessage(@Observes JsonObject rcvMessage) {
        log.info("received message inventory");
        if (Json.createReader(rcvMessage.createInputStream("UTF-8")).readObject() != null) {
            JsonObject orderJson = rcvMessage;
            Order order = Transformers.jsonToOrder(orderJson);
            order.getItemList().forEach(orderItem -> {
                int old_quantity = catalogService.getCatalogItemById(orderItem.getProductId()).getInventory().getQuantity();
                int new_quantity = old_quantity - orderItem.getQuantity();
                if (new_quantity < LOW_THRESHOLD) {
                    log.info("Inventory for item " + orderItem.getProductId() + " is below threshold (" + LOW_THRESHOLD + "), contact supplier!");
                } else {
                    orderItem.setQuantity(new_quantity);
                }
            });
        }
    }

    // Additional Information
}
