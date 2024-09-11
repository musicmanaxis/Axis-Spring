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
        throw new Exception();
        //여기서 발생시킨것이 1.아래의 @ExceptionHandler(value = Exception.class)이 붙은 메서드에서 우선 처리되고
        // 1번의 어노테이션을 주석처리하면
        // 2.AxisSpringExceptionHandler클래스의 ExceptionHandler(Exception e)메서드로 전달함으로서 2번째 순위로 처리된다
        //1번이 없으면  모든 예외가 발생하면 2번에서 AxisSpringExceptionHandler에서 처리하기로 어노테이션을 붙임
    }

    @ExceptionHandler(value = Exception.class)  //지역설정 예외처리(전역설정보다 더 우선순위)
    public ResponseEntity<Map<String, String>> ExceptionHandler(Exception e){
        HttpHeaders responseHeadres =new HttpHeaders();
        HttpStatus httpStatus=HttpStatus.BAD_REQUEST;
        //HttpStatus: enum클래스이고 예외에 대해서 상수값인 BAD_REQUEST를 발생시킨다....
        //"/exception"페이지에서 일부러 예외를 발생시키고 여기 메서드에서 처리하는 방식

        System.out.println("httpStatus="+httpStatus.value()+", 내용:"+httpStatus.getReasonPhrase());
        System.out.println("responseHeadres="+responseHeadres);
        LOGGER.info("Exception e의 내용->"+e.getMessage());  //뭐가 들어오는지 보자
        LOGGER.info("Controller 내 ExceptionHandler 호출");

        Map<String, String> map=new HashMap<>();
        map.put("Error Type:", httpStatus.getReasonPhrase()); //
        map.put("Code", "400");
        map.put("Message", "에러발생");

        ResponseEntity re=new ResponseEntity<>(map, responseHeadres, httpStatus);
        System.out.println("ResponseEntity의 내용:"+re);
        return re;

        //ResponseEntity 클래스에는 여러타입의 생성자중 3개 매개변수를 가져와서 에러에 대한 내용을 담았다.



    }



}
