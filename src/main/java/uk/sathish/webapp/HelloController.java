package uk.sathish.webapp;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/webapp")
public class HelloController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
