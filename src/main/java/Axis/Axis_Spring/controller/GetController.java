package Axis.Axis_Spring.controller;

import Axis.Axis_Spring.dto.MemberDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/get-api")   //공통되는 url을 사용하기 위해서 사용  밑에 각 @GetMapping의 상위페이지
public class GetController {

    @GetMapping(value="variable1/{variable}")
    public String getVaiable1(@PathVariable String variable){
        return variable;
    }
    // http://localhost:8080/api/v1/get-api/variable1/{String 값}
    //  http://localhost:8080/api/v1/get-api/variable1/안녕하세요  ->이렇게 해볼것

    @GetMapping(value="variable2/{variable}")
    public String getVaiable2(@PathVariable("variable") String var){
        return var;
    }
    // http://localhost:8080/api/v1/get-api/variable1/{String 값}

    @GetMapping(value = "request1")
    public String getRequestParam(@RequestParam String name,
                                  @RequestParam String email,
                                  @RequestParam String group){
        return "이름:"+name+", 이메일:"+email+", 조직:"+group;
    }
    // http://localhost:8080/api/v1/get-api/request1?name=erlia&email=erlia@naver.com&group=axis 웹페지에 입력확인


    @GetMapping(value = "request2")  //이방법은 입력것이 url에 무작위로 들어올때 쓰는 방법
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map -> {sb.append(map.getKey() + ":" + map.getValue() + "\n"); });
        return sb.toString();   //스트링빌더를 스트링으로 변환해서 출력
    }
    // http://localhost:8080/api/v1/get-api/request2?name=마&email=엑시스@naver.com&group=하하하&sex=남자


    //키와 값이 정해져 있지만 받아야할 파라미터가 많은 결우 DTO 객체를 이용하는 방식
    @GetMapping(value = "request3")
    public String getRequestParam3(MemberDTO memberDTO){
        return memberDTO.toString();
    }
    // http://localhost:8080/api/v1/get-api/request3?name=미현&email=엑시스@naver.com&group=하하하&sex=여자
}
