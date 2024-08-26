package Axis.Axis_Spring.data.dao.impl;

import Axis.Axis_Spring.data.dao.ProductDAO;
import Axis.Axis_Spring.data.entity.ProductEntity;
import Axis.Axis_Spring.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class ProductDAOImpl implements ProductDAO {
    ProductRepository productRepository;

    @Autowired  //미리 띄워져잇는 productRepository를 주입
    public ProductDAOImpl(ProductRepository productRepository){

        this.productRepository=productRepository;
    }

    @Override
    public ProductEntity saveProduct(ProductEntity productEntity){   //DB에 저장
        productRepository.save(productEntity);
        return  productEntity;
    }

    @Override
    public ProductEntity getProduct(String productId) {
        ProductEntity productEntity=productRepository.getById(productId);
        return productEntity;
    }




}
