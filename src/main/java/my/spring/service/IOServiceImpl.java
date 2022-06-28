package my.spring.service;

import org.springframework.context.MessageSource;

import java.util.Locale;
import java.util.Scanner;

public class IOServiceImpl implements IOService {

    private final Scanner scanner;
    private MessageSource messages;

    IOServiceImpl(MessageSource messages) {
        this.messages = messages;
        scanner = new Scanner(System.in);
    }

    @Override
    public void outputString(String s) { System.out.print(s); }

    @Override
    public void outputStringln(String s) { System.out.println(s); }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public int getUserInputDigitChoise(int upperBound) {

        int userChoice;

        while (true) {
            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                if (userChoice >= 1 && userChoice <= upperBound) {break;}
            } else {
                scanner.next();}

            outputString("\n" + messages.getMessage("prop.io.enter.number", null,
                    Locale.getDefault()) + upperBound + ": ");
        }

        return userChoice;
    }

}
