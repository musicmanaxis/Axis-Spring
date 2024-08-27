package Axis.Axis_Spring.data.handler.impl;

import Axis.Axis_Spring.data.dao.ProductDAO;
import Axis.Axis_Spring.data.entity.ProductEntity;
import Axis.Axis_Spring.data.handler.ProductDataHandler;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductDataHandlerImpl implements ProductDataHandler {
    ProductDAO productDAO;

    @Autowired
    public ProductDataHandlerImpl(ProductDAO productDAO){
        this.productDAO=productDAO;
    }

    @Override
    public ProductEntity saveProductEntity(String productId, String productName, int productPrice, int productStock) {
       ProductEntity productEntity=new ProductEntity(productId, productName, productPrice, productStock);
       return  productDAO.saveProduct(productEntity);
     }

    @Override  //DB에서 엔티티를 불러오는 단계
    public ProductEntity getProductEntity(String productId) {
       return productDAO.getProduct((productId));
    }
}
