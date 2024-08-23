package Axis.Axis_Spring.controller;

import Axis.Axis_Spring.dto.MemberDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {

    @PutMapping(value = "/default")
    public String putMethod(){
        return "Put Melthod";
    }

    @PutMapping(value = "/member")
    public String postMember(@RequestBody Map<String, Object> putData){
        StringBuilder sb=new StringBuilder();

        putData.entrySet().forEach(map->{
            sb.append(map.getKey()+":"+map.getValue()+"\n");
        }
        );

        return sb.toString();
    }


    //아래 3가지가 핵심포인트
    //1.toString을 이용하는 방법..결과값 모양이 달라진다.
    @PutMapping(value = "/member1")
    public String postMemberDto1(@RequestBody MemberDTO memberDTO){
        return memberDTO.toString();
    }
    //http://localhost:8080/api/v1/put-api/member1

    //2.Json형식 그대로 보여줌..일반적으로 클라이언트쪽에서도 제이슨 형식을 사용하므로
    // 이방법을 더 선호
    @PutMapping(value = "/member2")
    public MemberDTO postMemberDto2(@RequestBody MemberDTO memberDTO){
        return memberDTO;
    }
    //http://localhost:8080/api/v1/put-api/member2

    @PutMapping(value = "/member3")
    public ResponseEntity<MemberDTO> postMemberDto3(@RequestBody MemberDTO memberDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberDTO);
    }
    //http://localhost:8080/api/v1/put-api/member3

}
