package Axis.Axis_Spring.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/get-api")
public class DeleteController {

    @DeleteMapping(value ="/delete/{variable}")   //{variable} =String variable 을 같게...
    public String DeleteVariable(@PathVariable String variable){
        return variable;
    }

    //원래는 {variable}으로 디비에서 조회를 하고 처리되었다는 결과 메세지를
    // return variable 자리에 보여주는 것이다.
}
