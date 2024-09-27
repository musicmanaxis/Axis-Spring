package Axis.Axis_Spring.controller;

import Axis.Axis_Spring.data.dto.ProductDto;
import Axis.Axis_Spring.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean
    ProductService productService;

    // http://localhost:8080/api/v1/product-api/product/{productId}
    @Test
    @DisplayName("Product 데이터 가져오기 테스트")
    void getProductTest() throws Exception {

        // given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        //productService.getProduct("12315")가 수행이 될때 .willReturn()이 수행될거다.
        //getProduct()가 ProductDto를 리턴하기 때문에 willReturn값도 ProductDto객체를 넘겨주어야 한다.
        given(productService.getProduct("12315"))
                .willReturn(new ProductDto("15871", "pen", 5000, 2000));

        String productId = "12315";

         //perform:restAPI test를 해주는 환경을 만들어준다.
         //.perform(get :실제로 어떤 통신을 할 것인지 정의->httpRequest를 날릴 예정
         // andExpect : 기대하는 값이 나왔는지 체크해볼 수 있는 메소드
         //request를 하면 기존적으로 json형태의 body값을 받는다.->jsonPath를 사용해서 각각의 값이 존재하는지 확인

        System.out.println("#############/api/v1/product-api/product/의 get방식 Test 내용출력#############################");
        mockMvc
                .perform(get("/api/v1/product-api/product/" + productId))  //get방식 통신
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.productId").exists()) // json path의 depth가 깊어지면 .을 추가하여 탐색할 수 있음 (ex :
                // $.productId.productIdName)
                .andExpect(jsonPath("$.productName").exists())
                .andExpect(jsonPath("$.productPrice").exists())
                .andExpect(jsonPath("$.productStock").exists())
                .andDo(print());  //존재하는지 확인후 테스트한 내용을 출력


        // verify : 해당 객체의 메소드가 실행되었는지 체크해줌 ->productService객체의 getProduct가 실행되었는데 체크
        verify(productService).getProduct("12315");
    }

    // http://localhost:8080/api/v1/product-api/product
    @Test
    @DisplayName("Product 데이터 생성 테스트")
    void createProductTest() throws Exception {
        // Mock 객체에서 특정 메소드가 실행되는 경우 실제 Return을 줄 수 없기 때문에 아래와 같이 가정 사항을 만들어줌
        //given()의 의미:productService.saveProduct()의 내용이 실행되었다면.. ->willReturn을 해줘라..
        given(productService.saveProduct("15871", "pen", 5000, 2000))
                .willReturn(new ProductDto("15871", "pen", 5000, 2000));

        ProductDto productDto =
                ProductDto.builder()
                        .productId("15871")     //이값을 15871 대신 다른값으로 바꾸면 exception으로 처리된다.
                        .productName("pen")
                        .productPrice(5000)
                        .productStock(2000)
                        .build();
        Gson gson = new Gson();   //구글에서 json을 편하게 다루도록 제공한 라이브러리
        String content = gson.toJson(productDto);  //productDto객체를 json형태이 string값으로 만들어줌.

        // 아래 코드로 json 형태 변경 작업을 대체할 수 있음
        // String json = new ObjectMapper().writeValueAsString(productDto);
        System.out.println("==============/api/v1/product-api/product/의 post방식 Test 내용출력=========================");
        mockMvc
                .perform(
                        post("/api/v1/product-api/product")   //post방식 통신
                                .content(content)   //어떤 body값을 넘겨줄건지 지정
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  //상태가 ok인지
                .andExpect(jsonPath("$.productId").exists())  //productId값이 존재하는지..
                .andExpect(jsonPath("$.productName").exists())
                .andExpect(jsonPath("$.productPrice").exists())
                .andExpect(jsonPath("$.productStock").exists())
                .andDo(print());

                //post("/api/v1/product-api/product").content(content).contentType(MediaType.APPLICATION_JSON))으로 요청을 했을 때
                //andExpect으로 기대하는 값을 체크
        verify(productService).saveProduct("15871", "pen", 5000, 2000);
    }
}