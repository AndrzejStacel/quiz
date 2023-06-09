package pl.sdacademy.projektplus.quiz;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.sdacademy.projektplus.quiz.database.entities.PlayerEntity;
import pl.sdacademy.projektplus.quiz.database.repositories.PlayerRepository;
import pl.sdacademy.projektplus.quiz.services.QuizDataService;

import java.util.List;

@Component   // bean springowy
@Log
public class StartupRunner implements CommandLineRunner {

    @Autowired   // bean injection
//    private EntityManager entityManager;
    private PlayerRepository playerRepository;

    @Autowired
    private QuizDataService quizDataService;

    @Override
//    @Transactional
    public void run(String...args) throws Exception {
        log.info("Executing startup program...");

//        PlayerEntity playerNull = new PlayerEntity();
//        log.info("Created playerNull: " + playerNull);
//        PlayerEntity player = new PlayerEntity("John");
//        log.info("Created player: " + player);

//        entityManager.persist(player);
//        log.info("Same player after persist: " + player);
//        log.info("List of players from database:");
//        Query query = entityManager.createQuery(" SELECT player FROM PLAYERS player");
//        List<PlayerEntity> playersFromDb =
//                (List<PlayerEntity>) query.getResultList();
//        for (PlayerEntity playerFromDb : playersFromDb) {
//            log.info("Player from DB: " + playerFromDb);
//        }

        playerRepository.save(new PlayerEntity("John"));
        playerRepository.save(new PlayerEntity("Harry"));
        playerRepository.save(new PlayerEntity("Olga"));

        log.info("List of players from database:");
        List<PlayerEntity> playersFromDatabase = playerRepository.findAll();
        for (PlayerEntity player : playersFromDatabase) {
            log.info("Retrieved player: " + player);
        }

//        quizDataService.getQuizCategories();
//        quizDataService.getQuizQuestions();


    }

}
