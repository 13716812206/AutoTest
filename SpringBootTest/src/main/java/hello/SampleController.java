package hello;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@RestController
public class SampleController {

    public static void main(String[] args) {
        SpringApplication.run(SampleController.class, args);
    }
    @ResponseBody
    @RequestMapping("/index")
    public String index() {
        return "Hello Spring Boot!";
    }


}