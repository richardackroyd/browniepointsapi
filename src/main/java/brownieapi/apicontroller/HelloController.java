package brownieapi.apicontroller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String provideGreetings() {
        return "Greetings from Spring Boot and Rich with profiles and tidied class!";
    }

}
