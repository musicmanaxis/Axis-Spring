package Axis.Axis_Spring.service.impl;

import Axis.Axis_Spring.data.dto.ProductDto;

public class ProductService {

    String productId;
    String productName;
    int productPrice;
    int productStock;

    public ProductDto getProduct(String productId){
        return null;
    }

    public ProductDto saveProduct(String productId, String productName, int productPrice, int productStock){
        this.productId=productId;
        this.productName=productName;
        this.productPrice=productPrice;
        this.productStock=productStock;

        return null;

    }
}
