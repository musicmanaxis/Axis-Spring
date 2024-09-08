package Axis.Axis_Spring.controller;

import Axis.Axis_Spring.data.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Axis.Axis_Spring.service.ProductService;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/product-api")
public class ProductController {

    private ProductService productService;  //프로그래머가 작성한 클래스

    @Autowired   //생성자 부분에서 이 어노테이션을 쓰면 메모리에 떠 있는 productService를 끌어다 연결해준다.
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
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){

        String productId=productDto.getProductId();
        String productName=productDto.getProductName();
        int productPrice=productDto.getProductPrice();
        int productStock=productDto.getProductStock();

        ProductDto response=productService.saveProduct(productId, productName, productPrice, productStock);



        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);

    }

    //http://localhost:8080/api/v1/product-api/product/{productId}
    @DeleteMapping(value = "/product/{productId}")
    public ProductDto deleteProduct(@PathVariable String productId){
        return null;
    }
}
