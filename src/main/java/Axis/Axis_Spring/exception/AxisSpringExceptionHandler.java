package Axis.Axis_Spring.exception;

import Axis.Axis_Spring.controller.HelloController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice   //컨트롤러에서 발생하는 예외처리는 여기서 처리하겠다라는 뜻
public class AxisSpringExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(AxisSpringExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(Exception e){
        HttpHeaders responseHeadres =new HttpHeaders();
        HttpStatus httpStatus=HttpStatus.BAD_REQUEST;

        LOGGER.info(e.getLocalizedMessage());  //뭐가 들어오는지 보자
        LOGGER.info("Advice 내 ExceptionHandler 호출");

        Map<String, String> map=new HashMap<>();
        map.put("Error Type:", httpStatus.getReasonPhrase());
        map.put("Code", "400");
        map.put("Message", "에러발생");

        return new ResponseEntity<>(map, responseHeadres, httpStatus);
    }

}
