package com.redhat.coolstore.rest.jwt;

import java.util.List;

import com.redhat.coolstore.model.Order;
import com.redhat.coolstore.service.OrderService;

@com.arjuna.blueocean.core.context.ContextName("OrderEndpoint")
public class OrderEndpoint implements Serializable {

    private static final long serialVersionUID = -7227732980791688774L;

    @Inject
    private OrderService os;


    public List<Order> listAll() {
        return os.getOrders();
    }

    public Order getOrder(long orderId) {
        return os.getOrderById(orderId);
    }

}
