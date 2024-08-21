package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
API문서를 자동으로 생성해주는 도구가 스웨거인데..spring 3부터는 swagger ui가 정상적으로 동작하지 않기 때문에 변경이 필요하다
*/


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("Axis.Axis_Spring"))  //스웨거가 api를 만들 범위를 적는다..
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return  new ApiInfoBuilder()
                .title("API Document with Swagger 2.9.2")
                .description("설명부분:여기서 설명할것 적는다.")
                .version("1.0.0")  // pom.xml에서 설정한것일 이용 <version>1.0.0</version>
                .build();  //이것을 해야 위에 설정한것이 적용된다.
    }


}
