package my.spring.service;

import my.spring.dao.QuizDao;
import my.spring.domain.Person;
import my.spring.domain.Quiz;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizDao dao;
    private Person personQuiz;
    private final IOService ioService;
    private List<Quiz> listOfQuiz;
    private List<Integer> listPersonAnswers;
    private int countRightQuestions;
    private boolean isPlayQuiz = false;

    public QuizServiceImpl(QuizDao dao) {
        this.dao = dao;
        this.ioService = new IOServiceImpl();
        this.listOfQuiz = new ArrayList<Quiz>();
        this.listPersonAnswers = new ArrayList<Integer>();
    }

    public void initQuizService() {

        try {
            dao.readFileCsv();
            this.listOfQuiz = dao.getListOfQuiz();
            this.countRightQuestions = dao.getCountRightQuestions();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void createPersonByName() {

        ioService.outputStringln("\n" + "Welcome. Enter your name: ");
        personQuiz = dao.findPersonByName(ioService.getUserInput());
    }

    public void showMenu() {

        Boolean exitMenu = false;

        ioService.outputStringln("\n" + "------------------MENU------------------");
        ioService.outputStringln("1. Show list of questions and answers");
        ioService.outputStringln("2. Start Quiz");
        ioService.outputStringln("3. Exit");
        ioService.outputString("\n" + "Your answer: ");

        while (!exitMenu) {
            switch (ioService.getUserInputDigitChoise(3)) {
                case 1:
                    showListofQuiz();
                    exitMenu = true;
                    break;
                case 2:
                    playQuiz();
                    exitMenu = true;
                    break;
                case 3:
                    exitMenu = true;
                    break;
                default:
            }
        }
    }

    public void playQuiz() {

        ioService.outputStringln("\n" + "------------------Let's start the quiz------------------");
        ioService.outputStringln("\n" + "Choose the correct answer to the quetsion: ");

        for (var instanceQuiz : listOfQuiz) {
            instanceQuiz.showQuestionQuiz();
            instanceQuiz.showAnswersQuiz();

            ioService.outputString("\n" + "Your answer: ");
            listPersonAnswers.add(ioService.getUserInputDigitChoise(instanceQuiz.getAnswers().size()));
        }

        isPlayQuiz = true;
    }

    public void showListofQuiz() {

        ioService.outputStringln("\n" + "------------------List of Quiz------------------");

        for (var instanceQuiz : listOfQuiz) {
            instanceQuiz.showQuestionQuiz();
            instanceQuiz.showAnswersQuiz();
        }
    }

    public void checkResultOfQuiz() {

        int result = 0;

        if (isPlayQuiz) {
            ioService.outputStringln("\n" + "------------------" + personQuiz.getName() +
                                          " your answers below------------------" + "\n");

            try {
                for (int i = 0; i < listOfQuiz.size(); i++) {
                    if (listOfQuiz.get(i).getRightAnswer() == listPersonAnswers.get(i)) {
                        ioService.outputStringln("for " + (i+1) + " question, answer is " + listPersonAnswers.get(i) + " - correct");
                        result++;
                    } else {
                        ioService.outputStringln("for " + (i+1) + " question, answer is " + listPersonAnswers.get(i) + " - incorrect");}
                }
            } catch (IndexOutOfBoundsException ex) {
                ioService.outputStringln(ex.getMessage());
            }

            ioService.outputStringln("\n" + "Total number of correct answers is : " + result);

            if (result >= countRightQuestions) { ioService.outputStringln("\n" + "Congratulations!!! You have won the quiz!!!");
            } else { ioService.outputStringln("\n" + "Sorry, you failed the quiz. You need to give correct answers to " +
                        countRightQuestions + " or more questions."); }

            ioService.outputStringln("Thanks for taking the quiz.");

        }
    }
}
