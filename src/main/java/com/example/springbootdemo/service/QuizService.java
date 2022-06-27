package com.example.springbootdemo.service;

public interface QuizService {

    // Инициализируем сервис
    void initQuizService();

    // Создаем пользователя для викторины
    void createPersonByName();

    // Выводим меню пользователя
    void showMenu();

    // Запускаем викторину
    void playQuiz();

    // Отображаем вопросы и ответы викторины
    void showListofQuiz();

    // Подводим итоги викторины
    void checkResultOfQuiz();
}
