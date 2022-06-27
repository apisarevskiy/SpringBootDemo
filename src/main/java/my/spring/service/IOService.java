package my.spring.service;

public interface IOService {

    // Получаем числовой выбор пользователя на клавиатуре
    int getUserInputDigitChoise(int upperBound);

    // Получаем произвольный пользовательский ввод с клавиатуры
    String getUserInput();

    void outputString(String s);

    void outputStringln(String s);
}
