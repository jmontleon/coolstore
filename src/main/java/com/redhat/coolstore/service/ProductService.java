package com.redhat.coolstore.service;

import com.redhat.coolstore.model.CatalogItemEntity;
import com.redhat.coolstore.model.Product;
import com.redhat.coolstore.transformers.Transformers;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductService {

    private CatalogService cm;

    public ProductService(CatalogService cm) {
        this.cm = cm;
    }

    public List<Product> getProducts() {
        return cm.getCatalogItems().stream().map(Transformers.ToProduct::transform).collect(Collectors.toList());
    }

    public Product getProductByItemId(String itemId) {
        CatalogItemEntity entity = cm.getCatalogItemById(itemId);
        if (entity == null)
            return null;

        // Return the entity
        return Transformers.ToProduct.transform(entity);
    }

}

