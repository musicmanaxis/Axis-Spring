package Axis.Axis_Spring.controller;

import Axis.Axis_Spring.dto.MemberDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

    @PostMapping(value = "/default")
    public String postMethod(){
        return "Post Hello World";
    }

    // http://localhost:8080/api/v1/post-api/default
/*
 아래는 json형태로 바디값에 덧붙여서 전송해봐서 아래 2개의 메서드를 실행하는 방식이다.

        {
          "name":"erlia",
          "email":"erlia@naver.com",
           "group":"axis",
          "sex":"man"
        }
   */

/*
  @RequestBody 어노테이션은 HTTP 요청의 본문(body)에 포함된 JSON, XML 또는
  기타 포맷의 데이터를 Java 객체로 변환하는 역할을 합니다.
  이 어노테이션을 사용하면 클라이언트가 요청 본문에 포함한 데이터를 쉽게 접근할 수 있습니다.
*/


    @PostMapping(value = "/member")
    public String postMember(@RequestBody Map<String, Object> postData){  //value값이 어떤게 들어올지 몰라서 Object로 설정
        StringBuilder sb=new StringBuilder();

        postData.entrySet().forEach(map->{sb.append(map.getKey()+":"+map.getValue()+"\n"); });

        return sb.toString();
    }
    // http://localhost:8080/api/v1/post-api/member

    @PostMapping(value = "/member2")
    public String postMember2(@RequestBody MemberDTO memberDTO){
        return memberDTO.toString();
    }

    // http://localhost:8080/api/v1/post-api/member2
}
