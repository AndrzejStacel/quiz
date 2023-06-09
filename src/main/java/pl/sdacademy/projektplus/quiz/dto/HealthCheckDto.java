package pl.sdacademy.projektplus.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HealthCheckDto {
    private boolean status;
    private String message;


}


