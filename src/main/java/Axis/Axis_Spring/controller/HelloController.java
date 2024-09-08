package Axis.Axis_Spring.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@RestController   //Controller라는 것을 알려주는 역할
public class HelloController {

    private final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    //로그를 사용하기 위해서는 해당 클래스의 이름을 적는다.HelloController.class

    //@RequestMapping(value = "/hello", method = RequestMethod.GET)   ->예전방식
    @GetMapping("/hello")
    public String hello(){
        return "Hello World Axis Spring!!";
    }


    //http://localhost:8080/log-test 를 post방식으로 보내면..인텔리제이 하단 로그가 출력되는 것을 확인가
    @PostMapping("/log-test")
    public void logTest() {

        LOGGER.trace("Trace Log");
        LOGGER.debug("Debug Log");
        LOGGER.info("Info Log");
        LOGGER.warn("Warn Log");
        LOGGER.error("Error Log");
    }

    @PostMapping("/exception")
    public void exceptionTest() throws Exception{
        throw new Exception();  //여기서 발생시킨것이 public ResponseEntity<Map<String, String>> 으로 전달
    }

    public ResponseEntity<Map<String, String>> ExceptionHandler(Exception e){
        HttpHeaders responseHeadres =new HttpHeaders();
        HttpStatus httpStatus=HttpStatus.BAD_REQUEST;

        LOGGER.info(e.getLocalizedMessage());  //뭐가 들어오는지 보자
        LOGGER.info("Controller 내 ExceptionHandler 호출");

        Map<String, String> map=new HashMap<>();
        map.put("Error Type:", httpStatus.getReasonPhrase());
        map.put("Code", "400");
        map.put("Message", "에러발생");

        return new ResponseEntity<>(map, responseHeadres, httpStatus);
    }



}
