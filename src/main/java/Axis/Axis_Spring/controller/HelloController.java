package Axis.Axis_Spring.controller;

import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


}
