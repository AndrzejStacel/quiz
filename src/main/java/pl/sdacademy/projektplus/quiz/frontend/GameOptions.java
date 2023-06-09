package pl.sdacademy.projektplus.quiz.frontend;

import lombok.Data;

import static pl.sdacademy.projektplus.quiz.frontend.Difficulty.EASY;

@Data
public class GameOptions {
    private int numberOfQuestions = 5;
    private Difficulty difficulty = EASY;
    private int categoryId = 9;



}
