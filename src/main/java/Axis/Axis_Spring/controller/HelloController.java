package Axis.Axis_Spring.controller;

import org.springframework.web.bind.annotation.*;

@RestController   //Controller라는 것을 알려주는 역할
public class HelloController {
    //@RequestMapping(value = "/hello", method = RequestMethod.GET)   ->예전방식
    @GetMapping("/hello")
    public String hello(){
        return "Hello World Axis Spring!!";
    }


}
