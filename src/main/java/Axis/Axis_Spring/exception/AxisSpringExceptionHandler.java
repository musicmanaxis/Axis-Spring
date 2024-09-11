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

@RestControllerAdvice //전역설정(지역설정이 더 우선순위에 있다.)
//모든 컨트롤러에서 발생하는 예외처리는 여기서 정의하고 처리하겠다라는 의미
public class AxisSpringExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(AxisSpringExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    //@ExceptionHandler 이것을 사용하면 발생하는 예외마다 처리할 메서드를 저의
    public ResponseEntity<Map<String, String>> ExceptionHandler(Exception e){
        HttpHeaders responseHeadres =new HttpHeaders();
        HttpStatus httpStatus=HttpStatus.BAD_REQUEST;

        LOGGER.info("e.getLocalizedMessage()="+e.getLocalizedMessage());
        //하단 콘솔창에서 뭐가 들어오는지 보자
        LOGGER.info("Advice 내 AxisSpringExceptionHandler 클래스에서 호출");

        Map<String, String> map=new HashMap<>();
        map.put("Error Type:", httpStatus.getReasonPhrase());
        map.put("Code", "400");
        map.put("Message", "에러발생");

        return new ResponseEntity<>(map, responseHeadres, httpStatus);
    }

}
