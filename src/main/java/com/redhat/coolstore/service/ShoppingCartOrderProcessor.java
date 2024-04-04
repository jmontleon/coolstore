package com.redhat.coolstore.service;

import java.util.logging.Logger;
import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.Emitter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.redhat.coolstore.model.ShoppingCart;
import com.redhat.coolstore.utils.Transformers;

@ApplicationScoped
public class ShoppingCartOrderProcessor  {

    @Inject
    Logger log;


    @Inject
    private transient JMSContext context;

    @Resource(name = "orders")
    @ConfigProperty(name = "mp.messaging.outgoing.orders.topic")
    private Emitter<String> ordersEmitter;

    
  
    public void  process(ShoppingCart cart) {
        log.info("Sending order from processor: ");
        ordersEmitter.emit("shoppingCartToJson(cart)");
    }



}
