package Axis.Axis_Spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    //@RequestMapping(value = "/hello", method = RequestMethod.GET)   ->예전방식
    @GetMapping("/hello")
    public String hello(){
        return "Hello World Axis Spring";
    }
}
