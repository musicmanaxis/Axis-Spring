package Axis.Axis_Spring.data.handler;

import Axis.Axis_Spring.data.entity.ProductEntity;

public interface ProductDataHandler {

    public ProductEntity saveProductEntity(String productId, String productName, int productPrice, int productStock);
    public ProductEntity getProductEntity(String productId) ;
}
