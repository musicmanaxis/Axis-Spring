package Axis.Axis_Spring.data.dao;

import Axis.Axis_Spring.data.entity.ProductEntity;

public interface ProductDAO {
    ProductEntity saveProduct(ProductEntity productEntity);
    ProductEntity getProduct(String productId);

    ProductEntity getProudct(String productId);
}
