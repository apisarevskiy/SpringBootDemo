package my.spring.service;

import my.spring.dao.QuizDao;
import my.spring.domain.Person;
import my.spring.domain.Quiz;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizDao dao;
    private Person personQuiz;
    private final IOService ioService;
    private List<Quiz> listOfQuiz;
    private List<Integer> listPersonAnswers;
    private int countRightQuestions;
    private boolean isPlayQuiz = false;
    private MessageSource messages;

    public QuizServiceImpl(QuizDao dao, MessageSource messages) {
        this.dao = dao;
        this.ioService = new IOServiceImpl(messages);
        this.listOfQuiz = new ArrayList<Quiz>();
        this.listPersonAnswers = new ArrayList<Integer>();
        this.messages = messages;
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

        ioService.outputStringln("\n" + messages.getMessage("prop.welcome", null, Locale.getDefault()));
        personQuiz = dao.findPersonByName(ioService.getUserInput());
    }

    public void showMenu() {

        Boolean exitMenu = false;

        ioService.outputStringln("\n" + messages.getMessage("prop.list.menu", null, Locale.getDefault()));
        ioService.outputStringln(messages.getMessage("prop.list.menu.first", null, Locale.getDefault()));
        ioService.outputStringln(messages.getMessage("prop.list.menu.two", null, Locale.getDefault()));
        ioService.outputStringln(messages.getMessage("prop.list.menu.three", null, Locale.getDefault()));
        ioService.outputString("\n" + messages.getMessage("prop.list.menu.answer", null, Locale.getDefault()));

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

        ioService.outputStringln("\n" + messages.getMessage("prop.play.quiz.start", null, Locale.getDefault()));
        ioService.outputStringln("\n" + messages.getMessage("prop.play.quiz.question", null, Locale.getDefault()));

        for (var instanceQuiz : listOfQuiz) {
            instanceQuiz.showQuestionQuiz();
            instanceQuiz.showAnswersQuiz();

            ioService.outputString("\n" + messages.getMessage("prop.list.menu.answer", null, Locale.getDefault()));
            listPersonAnswers.add(ioService.getUserInputDigitChoise(instanceQuiz.getAnswers().size()));
        }

        isPlayQuiz = true;
    }

    public void showListofQuiz() {

        ioService.outputStringln("\n" + messages.getMessage("prop.list.quiz", null, Locale.getDefault()));

        for (var instanceQuiz : listOfQuiz) {
            instanceQuiz.showQuestionQuiz();
            instanceQuiz.showAnswersQuiz();
        }
    }

    public void checkResultOfQuiz() {

        int result = 0;

        if (isPlayQuiz) {
            ioService.outputStringln("\n" + "------------------" + personQuiz.getName() + " " +
                                          messages.getMessage("prop.result.quiz.answers.below", null,
                                                  Locale.getDefault()) + "\n");

            try {
                for (int i = 0; i < listOfQuiz.size(); i++) {
                    if (listOfQuiz.get(i).getRightAnswer() == listPersonAnswers.get(i)) {
                        ioService.outputStringln(messages.getMessage("prop.result.quiz.for", null, Locale.getDefault()) + " " + (i+1)
                                + " " + messages.getMessage("prop.result.question.answer", null, Locale.getDefault()) +
                                listPersonAnswers.get(i) + " " + messages.getMessage("prop.result.correct", null, Locale.getDefault()));
                        result++;
                    } else {
                        ioService.outputStringln(messages.getMessage("prop.result.quiz.for", null, Locale.getDefault()) + " " + (i+1)
                                + " " + messages.getMessage("prop.result.question.answer", null, Locale.getDefault()) +
                                listPersonAnswers.get(i) + " " + messages.getMessage("prop.result.incorrect", null, Locale.getDefault()));}
                }
            } catch (IndexOutOfBoundsException ex) {
                ioService.outputStringln(ex.getMessage());
            }

            ioService.outputStringln("\n" + messages.getMessage("prop.result.total.answer", null,
                    Locale.getDefault()) + result);

            if (result >= countRightQuestions) { ioService.outputStringln("\n" + messages.getMessage("prop.result.congratulation", null,
                    Locale.getDefault()));
            } else { ioService.outputStringln("\n" + messages.getMessage("prop.result.failed", null,
                    Locale.getDefault()) + countRightQuestions + " " + messages.getMessage("prop.result.more.question", null,
                    Locale.getDefault())); }

            ioService.outputStringln(messages.getMessage("prop.result.thanks", null,
                    Locale.getDefault()));

        }
    }
}
