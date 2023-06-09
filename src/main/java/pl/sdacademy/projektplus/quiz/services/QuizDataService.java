package pl.sdacademy.projektplus.quiz.services;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.sdacademy.projektplus.quiz.dto.CategoriesDto;
import pl.sdacademy.projektplus.quiz.dto.CategoryQuestionCountInfoDto;
import pl.sdacademy.projektplus.quiz.dto.QuestionsDto;
import pl.sdacademy.projektplus.quiz.frontend.Difficulty;
import pl.sdacademy.projektplus.quiz.frontend.GameOptions;

import java.net.URI;
import java.util.List;

@Service
@Log
public class QuizDataService {

    public List<CategoriesDto.CategoryDto> getQuizCategories() {
        RestTemplate restTemplate = new RestTemplate();
        CategoriesDto result =
                restTemplate.getForObject("https://opentdb.com/api_category.php",
                        CategoriesDto.class);
        log.info("Quiz categories: " + result.getCategories());
        return result.getCategories();
    }

    public List<QuestionsDto.QuestionDto> getQuizQuestions(GameOptions gameOptions) {
        CategoryQuestionCountInfoDto categoryQuestionCount = getCategoryQuestionCount(gameOptions.getCategoryId());
        int availableQuestionCount =
                categoryQuestionCount.getQuestionCountForDifficulty(gameOptions.getDifficulty());
        if (availableQuestionCount >= gameOptions.getNumberOfQuestions()) {
            return getQuizQuestions(gameOptions.getNumberOfQuestions(),
                    gameOptions.getCategoryId(), gameOptions.getDifficulty());
        }   else {
            return getQuizQuestions(availableQuestionCount,
                    gameOptions.getCategoryId(), gameOptions.getDifficulty());
        }
    }

    private List<QuestionsDto.QuestionDto> getQuizQuestions(int numberOfQuestions,
                                                            int categoryId, Difficulty difficulty) {

        RestTemplate restTemplate = new RestTemplate();

        URI uri =
                UriComponentsBuilder.fromHttpUrl("https://opentdb.com/api.php")
                        .queryParam("amount", numberOfQuestions)
                        .queryParam("category", categoryId)
                        .queryParam("difficulty", difficulty.name().toLowerCase())
//                        .queryParam("encode", "url3986")
                        .build().toUri();
        log.info("Quiz question retrieve URL: " + uri);

        QuestionsDto result = restTemplate.getForObject(uri, QuestionsDto.class);

        log.info("Open Trivia DB response code: " + result.getResponseCode() +
                ". Questions: " + result.getResults());

        return result.getResults();
    }

    private CategoryQuestionCountInfoDto getCategoryQuestionCount(int categoryId) {
        RestTemplate restTemplate = new RestTemplate();
        URI uri =
                UriComponentsBuilder.fromHttpUrl("https://opentdb.com/api_count.php")
                        .queryParam("category", categoryId)
                        .build().toUri();
        log.info("Quiz category question count retrieve URL: " + uri);
        CategoryQuestionCountInfoDto result =
                restTemplate.getForObject(uri,CategoryQuestionCountInfoDto.class);
        log.info("Quiz category question count content: " + result);
        return result;
    }



}
