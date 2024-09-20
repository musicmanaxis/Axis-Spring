package Axis.Axis_Spring.service.impl;

import Axis.Axis_Spring.data.dto.MemberDTO;
import Axis.Axis_Spring.service.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {
    private final Logger LOGGER= LoggerFactory.getLogger(RestTemplateServiceImpl.class);

    @Override
    public String getAxis(){
        URI uri= UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/Axis")   //뒤에 붙는 경로
                .encode()    //UTF-8로 인코딩
                .build()
                .toUri();  //위에 build()로 반환이 component로 됨으로 uri로 변경

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(uri, String.class);
            //String.class->ResponseEntity<String>타입을 밎추기 위해서
            LOGGER.info("status code:{}", responseEntity.getStatusCode());
            LOGGER.info("body: {}", responseEntity.getBody());

            return responseEntity.getBody();
    }

    @Override
    public String getName(){
        URI uri= UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/name")   //뒤에 붙는 경로
                .queryParam("name", "Erlia")  //키값과 value값을 넣는다
                .encode()    //UTF-8로 인코딩
                .build()
                .toUri();  //위에 build()로 반환이 component로 됨으로 uri로 변경

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(uri, String.class);
        //String.class->ResponseEntity<String>타입을 밎추기 위해서
        LOGGER.info("status code:{}", responseEntity.getStatusCode());
        LOGGER.info("body: {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public String getName2(){
        URI uri= UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/path-variable/{name}")   //.expand("Erlia")의 erlia를 넣어주게 된다
                .encode()    //UTF-8로 인코딩
                .build()
                .expand("Erlia")  //복수의 값을 넣어야 할 경우 , 추가하여 구분
                .toUri();  //위에 build()로 반환이 component로 됨으로 uri로 변경

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(uri, String.class);
        //String.class->ResponseEntity<String>타입을 밎추기 위해서
        LOGGER.info("status code:{}", responseEntity.getStatusCode());
        LOGGER.info("body: {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public ResponseEntity<MemberDTO> postDto(){
        URI uri= UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/member")
                .queryParam("name", "Erlia")
                .queryParam("email", "erlia@navre.com")
                .queryParam("organization", "Axis")
                .encode()    //UTF-8로 인코딩
                .build()
                .toUri();  //위에 build()로 반환이 component로 됨으로 uri로 변경

        MemberDTO memberDTO=new MemberDTO();
        memberDTO.setName("erlia");
        memberDTO.setEmail("aaa@ssa.com");
        memberDTO.setGroup("Axis");

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity=restTemplate.postForEntity(uri, memberDTO, MemberDTO.class);

        LOGGER.info("status code:{}", responseEntity.getStatusCode());
        LOGGER.info("body: {}", responseEntity.getBody());

        return responseEntity;

    }

    @Override
    public ResponseEntity<MemberDTO> addHeader(){
        URI uri= UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/add-header")
                .encode()    //UTF-8로 인코딩
                .build()
                .toUri();  //위에 build()로 반환이 component로 됨으로 uri로 변경

        MemberDTO memberDTO=new MemberDTO();
        memberDTO.setName("erlia");
        memberDTO.setEmail("aaa@ssa.com");
        memberDTO.setGroup("Axis");

        RequestEntity<MemberDTO> requestEntity=RequestEntity   //여기서 차이점
                .post(uri)   //post메서드를 사용하겠다.
                .header("Axis-Header", "Axis Spring")  //키값과 value값
                .body(memberDTO);

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity=restTemplate.exchange(requestEntity, MemberDTO.class);
        //MemberDTO.class->response받을 타입만 설정
        LOGGER.info("status code:{}", responseEntity.getStatusCode());
        LOGGER.info("body: {}", responseEntity.getBody());

        return responseEntity;

    }
}
