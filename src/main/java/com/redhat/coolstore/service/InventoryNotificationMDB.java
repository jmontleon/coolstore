package com.redhat.coolstore.service;

import com.redhat.coolstore.model.Order;
import com.redhat.coolstore.utils.Transformers;
import jakarta.inject.Inject;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import java.util.Hashtable;
import java.util.Properties;

public class InventoryNotificationMDB {

    private static final int LOW_THRESHOLD = 50;

    @Inject
    private CatalogService catalogService;

    @ConfigProperty(name = "mp.messaging.incoming.orders.topic")
    private String ordersTopic;

    @Inject
    @Channel(value = "orders")
    Emitter<String> orderEmitter;

    private final static String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";
    private final static String JMS_FACTORY = "TCF";
    private final static String TOPIC = "topic/orders";
    //private TopicConnection tcon;
    //private TopicSession tsession;
    //private TopicSubscriber tsubscriber;

    public void onMessage(Message rcvMessage) {
        TextMessage msg;
        {
            try {
                System.out.println("received message inventory");
                if (rcvMessage instanceof TextMessage) {
                    msg = (TextMessage) rcvMessage;
                    String orderStr = msg.getBody(String.class);
                    Order order = Transformers.jsonToOrder(orderStr);
                    order.getItemList().forEach(orderItem -> {
                        int old_quantity = catalogService.getCatalogItemById(orderItem.getProductId()).getInventory().getQuantity();
                        int new_quantity = old_quantity - orderItem.getQuantity();
                        if (new_quantity < LOW_THRESHOLD) {
                            System.out.println("Inventory for item " + orderItem.getProductId() + " is below threshold (" + LOW_THRESHOLD + "), contact supplier!");
                        } else {
                            orderItem.setQuantity(new_quantity);
                        }
                    });
                    orderEmitter.send(orderStr);
                }


            } catch (Exception jmse) {
                System.err.println("An exception occurred: " + jmse.getMessage());
            }
        }
    }

    public void init() throws NamingException, JMSException {
        //Context ctx = getInitialContext();
        //TopicConnectionFactory tconFactory = (TopicConnectionFactory) PortableRemoteObject.narrow(ctx.lookup(JMS_FACTORY), TopicConnectionFactory.class);
        //tcon = tconFactory.createTopicConnection();
        //tsession = tcon.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        //Topic topic = (Topic) PortableRemoteObject.narrow(ctx.lookup(TOPIC), Topic.class);
        //tsubscriber = tsession.createSubscriber(topic);
        //tsubscriber.setMessageListener(this);
        //tcon.start();
    }

    public void close() throws JMSException {
        //tsubscriber.close();
        //tsession.close();
        //tcon.close();
    }

    private static InitialContext getInitialContext() throws NamingException {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
        env.put(Context.PROVIDER_URL, "t3://localhost:7001");
        env.put("weblogic.jndi.createIntermediateContexts", "true");
        return new InitialContext(env);
    }
}
