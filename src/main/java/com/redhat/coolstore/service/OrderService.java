package com.redhat.coolstore.service;

import com.redhat.coolstore.model.Order;
import io.quarkus.hibernate.orm.Panache;
import io.quarkus.hibernate.orm.PanacheEntity;
import io.quarkus.hibernate.orm.PanacheEntityBase;
import io.quarkus.hibernate.orm.PanacheQuery;

@PanacheEntity(tableName = "orders")
public class OrderEntity extends PanacheEntityBase<OrderEntity> {

    public long id;
    // Add other fields of the Order class here

    public static OrderEntity findById(Long id) {
        return findById(OrderEntity.class, id);
    }
}

public class OrderService {

  public void save(Order order) {
    OrderEntity orderEntity = new OrderEntity();
    // Map the Order object to OrderEntity here
    orderEntity.persist();
  }

  public List<OrderEntity> getOrders() {
    return OrderEntity.listAll();
  }

  public OrderEntity getOrderById(long id) {
    return OrderEntity.findById(id);
  }
}
