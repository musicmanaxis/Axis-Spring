package Axis.Axis_Spring.data.dto;

import Axis.Axis_Spring.data.entity.ProductEntity;
import lombok.*;

//상품의 정보를 등록하거나 빼거나 수정, 조회 작업을 할려고 만들었다.
//데이터를 넘길려고 만듬
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDto {
    private String productId;
    private String productName;
    private int productPrice;
    private int productStock;

    public ProductEntity toEntity(){
        return ProductEntity.builder()
                .productId(productId)
                .productName(productName)
                .productPrice(productPrice)
                .productStock(productStock)
                .build();

    }

}
