package Axis.Axis_Spring.controller;

import Axis.Axis_Spring.dto.MemberDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@GetMapping 사용방법...

/*
@RestController
Rest한 컨트롤러임을 선언, @Controller와 @ResponseBody가 합쳐진 어노테이션이라고 생각하면 된다.
@Controller와 다르게 반환하려는 주류는 JSON 형태의 객체 데이터다.
REST API를 개발할 때 주로 사용하며 마찬가지로 ResponseEntity로 감싸서 주로 반환한다.*/

@RestController
@RequestMapping("api/v1/get-api")  //버전관리를 위해 V1이라고 표시...
//공통되는 url을 사용하기 위해서 사용,  밑에 각 @GetMapping의 상위페이지
public class GetController {

    @GetMapping(value = "/name")
    public String getName(){
        return "Erlia";
    }
    //http://localhost:8080/api/v1/get-api/name

    @GetMapping(value="/variable1/{variable}")
    public String getVaiable1(@PathVariable String variable){
        return variable;
    }
    //@PathVariable:Get형식 요청에서 파라미터를 전달하기 위해 URL에 값을 담아 전달하는 방법
    // {"variable"}의 이름과 String "variable"와 같이 이름을 일치시켜야 함

    // http://localhost:8080/api/v1/get-api/variable1/{String 값}
    //  http://localhost:8080/api/v1/get-api/variable1/안녕하세요  ->이렇게 해볼것

    @GetMapping(value="variable2/{variable}")
    public String getVaiable2(@PathVariable("variable") String var){
        return var;
    }
    // {"variable"}의 이름과 String "variable"와 같이 이름을 일치시킬수 없을 때 쓰는 방식

    // http://localhost:8080/api/v1/get-api/variable1/{String 값}

/*
    아래의 것은
    '?'를 기준으로 우측에 {키}={값}의 형태로 전달되며, 복수 형태로 전달할 경우 &를 사용하는
    방식을 말한다.
*/

    @GetMapping(value = "request1")
    public String getRequestParam(@RequestParam String name,   //각각의 들어오는 값을 이 객체들로 받는다.
                                  @RequestParam String email,
                                  @RequestParam String group){
        return "이름:"+name+", 이메일:"+email+", 조직:"+group;
    }
    //@RequestParam :Get 형식의 요청에서 쿼리 문자열을 전달하기 위해 사용하는 방법
    //'?'를 기준으로 우측에 {키}={값}의 형태로 전달되며, 복수 형태로 전달할 경우 &를 사용함
    // http://localhost:8080/api/v1/get-api/request1?name=erlia&email=erlia@naver.com&group=axis 웹페지에 입력확인
    //위에 값들을 @RequestParam String name 같은 객체에 넘겨준다.


    @GetMapping(value = "request2")  //이방법은 입력것이 url에 무작위로 들어올때 쓰는 방법
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();


        param.entrySet().forEach(map -> {sb.append(map.getKey() + ":" + map.getValue() + "\n"); });
        return sb.toString();   //스트링빌더를 스트링으로 변환해서 출력

    }
    //위 예시 코드는 어떤 요청값이 뭐가 들어올지 모를 경우 사용하는 방식
    //1. http://localhost:8080/api/v1/get-api/request2?name=마&email=엑시스@naver.com&group=하하하&sex=남자
    //2. http://localhost:8080/api/v1/get-api/request2?성별=골라보자
    //1.2번과 같이 아무렇게나 키=값 형태로 갯수와 상관없이 넘겨주면 그대로 출력된다.


    //키와 값이 정해져 있지만 받아야할 파라미터가 많을 경우 DTO 객체를 이용하는 방식
    @GetMapping(value = "request3")
    public String getRequestParam3(MemberDTO memberDTO){
       // return memberDTO.getName()+""+memberDTO.getEmail()+""+memberDTO.getGroup();  이런식으로 호출할수도 있다.
        return memberDTO.toString();
    }
    // http://localhost:8080/api/v1/get-api/request3?name=미현&email=엑시스@naver.com&group=하하하&sex=여자
    // DTO클래스의 멤버변수들에 각 파라미터가 대응된다.
}
