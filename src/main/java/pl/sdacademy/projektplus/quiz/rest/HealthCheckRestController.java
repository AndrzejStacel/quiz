package pl.sdacademy.projektplus.quiz.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sdacademy.projektplus.quiz.dto.HealthCheckDto;

@RestController
@RequestMapping("/api/health")
public class HealthCheckRestController {

    @GetMapping
    public HealthCheckDto healtcheck() {
        HealthCheckDto dto = new HealthCheckDto(true, "It's working");
        return dto;
    }


}
