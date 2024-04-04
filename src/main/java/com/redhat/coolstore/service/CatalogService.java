package com.redhat.coolstore.service;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.spi.PersistenceUnitInfo;
import javax.inject.Inject;

import org.hibernate.reactive.provider.ReactiveEntityManager;

import com.redhat.coolstore.model.*;

public class CatalogService {

    @Inject
    Logger log;

    @Inject
    private ReactiveEntityManager em;

    public CatalogService() {
    }

    public List<CatalogItemEntity> getCatalogItems() {
        return em.createQuery("SELECT i FROM CatalogItemEntity i", CatalogItemEntity.class).list();
    }

    public CatalogItemEntity getCatalogItemById(String itemId) {
        return em.find(CatalogItemEntity.class, itemId);
    }

    public void updateInventoryItems(String itemId, int deducts) {
        InventoryEntity inventoryEntity = getCatalogItemById(itemId).getInventory();
        int currentQuantity = inventoryEntity.getQuantity();
        inventoryEntity.setQuantity(currentQuantity-deducts);
        em.merge(inventoryEntity);
    }

}
