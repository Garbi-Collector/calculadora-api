package gabri.dev.javaspringcompose.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ping controller class to health check.
 */
@RestController
@RequestMapping
public class PingController {

    @GetMapping("/ping")
    public String pong() {
        return "pong";
    }

    @GetMapping("/suma")
    public int suma(
        @RequestParam int a,
        @RequestParam int b
    ){
        return a+b;
    }
}
