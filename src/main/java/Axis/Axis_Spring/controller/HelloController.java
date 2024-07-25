package Axis.Axis_Spring.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    //@RequestMapping(value = "/hello", method = RequestMethod.GET)   ->예전방식
    @GetMapping("/hello")
    public String hello(){
        return "Hello World Axis Spring!!";
    }


}
