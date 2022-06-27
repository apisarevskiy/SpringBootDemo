package com.example.springbootdemo.dao;

import my.spring.config.AppConfig;
import my.spring.domain.Person;
import my.spring.domain.Quiz;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class QuizDaoImpl implements QuizDao {

    private List<Quiz> listOfQuiz;
    private String pathFileCsv;
    private int countRightQuestions;

    QuizDaoImpl(AppConfig appConfig) {

        this.listOfQuiz = new ArrayList<Quiz>();
        this.pathFileCsv = appConfig.getPathFileCsv();
        this.countRightQuestions = appConfig.getCountRightQuestions();
    }

    public List<Quiz> getListOfQuiz() {
        return listOfQuiz;
    }

    public int getCountRightQuestions() {
        return countRightQuestions;
    }

    @Override
    public Quiz findQuiz(String questionQuiz, List<String> answersQuiz, int rightQuestion) {
        return new Quiz(questionQuiz, answersQuiz, rightQuestion);
    }

    public Person findPersonByName(String name) {
        return new Person(name);
    }
    @Override
    public void readFileCsv() {

        try (FileReader file = new FileReader(pathFileCsv)){

            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                var line = scanner.nextLine();
                String sentence[] = line.split(",");
                putListOfQuiz(sentence);
            }
            scanner.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void putListOfQuiz(String sentence[]) {

        List<String> answersQuiz = new ArrayList<String>();
        String questionQuiz = "";
        int rightAnswer = 0;
        int partOfSentence = 0;


        for (var quizSentence : sentence) {

            switch (partOfSentence) {
                case 0:
                    questionQuiz = quizSentence;
                    break;
                case 1:
                    rightAnswer = Integer.parseInt(quizSentence);
                    break;
                default:
                    answersQuiz.add(quizSentence);
            }
            partOfSentence++;
        }

        Quiz instanceQuiz = findQuiz(questionQuiz, answersQuiz, rightAnswer);
        listOfQuiz.add(instanceQuiz);
    }
}
