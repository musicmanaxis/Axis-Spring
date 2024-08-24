package Axis.Axis_Spring.data.dao.impl;

import Axis.Axis_Spring.data.dao.ProductDAO;
import Axis.Axis_Spring.data.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDAOImpl implements ProductDAO {
    ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    @Override
    public ProductEntity saveProduct(ProductEntity productEntity){   //DB에 저장
        productRepository.save(productEntity);
        return  productEntity;
    }

    @Override
    public ProductEntity getProudct(String productId){    //DB에서 가져오기
        ProductEntity productEntity=productRepository.getById(productId);
        return productEntity;
    }


}
