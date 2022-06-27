package my.spring.service;

import org.springframework.stereotype.Service;

@Service
public class ApplicationRunner {

    private QuizService quizService;

    public ApplicationRunner(QuizService quizService) {
        this.quizService = quizService;
    }
    public void runApplication() {

        quizService.initQuizService();
        quizService.createPersonByName();
        quizService.showMenu();
        quizService.checkResultOfQuiz();
    }

}
