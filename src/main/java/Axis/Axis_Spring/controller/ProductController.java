package Axis.Axis_Spring.controller;

import Axis.Axis_Spring.data.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Axis.Axis_Spring.service.ProductService;

@RestController
@RequestMapping("/api/v1/product-api")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
                this.productService=productService;
    }

    //http://localhost:8080/api/v1/product-api/product/{productId}
    //http://localhost:8080/api/v1/product-api/product/Axis-Book 이걸로 Get방식으로 해보자
    //Id로 조회하는 방식
    @GetMapping(value = "/product/{productId}")
    public ProductDto getProduct(@PathVariable String productId){
        return productService.getProduct(productId);
    }

    //http://localhost:8080/api/v1/product-api/product    post방식으로 실행할것
    /* 제이슨 내용
    {
        "productId":"Axis-Book",
        "productName":"Axis-Book-1",
        "productPrice":"5000",
        "productStock":"5"
    }
    */
    @PostMapping(value = "/product")
    public ProductDto createProduct(@RequestBody ProductDto productDto){

        String productId=productDto.getProductId();
        String productName=productDto.getProductName();
        int productPrice=productDto.getProductPrice();
        int productStock=productDto.getProductStock();

        return productService.saveProduct(productId, productName, productPrice, productStock);
    }

    //http://localhost:8080/api/v1/product-api/product/{productId}
    @DeleteMapping(value = "/product/{productId}")
    public ProductDto deleteProduct(@PathVariable String productId){
        return null;
    }
}
