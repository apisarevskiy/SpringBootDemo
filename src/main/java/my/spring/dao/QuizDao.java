package my.spring.dao;

import my.spring.domain.Person;
import my.spring.domain.Quiz;

import java.util.List;

public interface QuizDao {

    // Получаем список вопросов и ответов для викторины
    List<Quiz> getListOfQuiz();

    // Добавляем новый вопрос и ответы в список викторины
    void putListOfQuiz(String sentence[]);

    // Получаем количество верных ответов пользователя для прохождения викторины
    int getCountRightQuestions();

    // Получаем один элемент викторины, содержащий вопрос и ответы
    Quiz findQuiz(String questionQuiz, List<String> answersQuiz, int rightAnswer);

    // Получаем информацию о пользователе викторины
    Person findPersonByName(String name);

    // Читаем csv файл для формирования списка вопросов и ответов для викторины
    void readFileCsv();
}
