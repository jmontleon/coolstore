package com.redhat.coolstore.service;

import com.redhat.coolstore.model.Order;
import com.redhat.coolstore.utils.Transformers;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.ReactiveMessagingException;
import org.jboss.logging.Logger;

import javax.inject.Inject;

public class OrderServiceMDB {

    @Inject
    OrderService orderService;

    @Inject
    CatalogService catalogService;

    private static final Logger LOGGER = Logger.getLogger(OrderServiceMDB.class);

    @Inject
    @Channel("topic/orders")
    Receiver receiver;

    public void start() {
        receiver.receive()
                .map(Message::getPayload)
                .map(String::toString)
                .map(Transformers::<Order>jsonToOrder)
                .ifPresent(order -> {
                    LOGGER.info("Received order: " + order);
                    orderService.save(order);
                    order.getItemList().forEach(orderItem -> {
                        catalogService.updateInventoryItems(orderItem.getProductId(), orderItem.getQuantity());
                    });
                })
                .onFailure().invoke(ex -> LOGGER.error("Error processing message: " + ex.getMessage()));
    }

    public interface Receiver {
        void receive();
    }
}
