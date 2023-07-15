package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
public class App {
    public static long getCountOfFreeEmails(List<String> emailsList) {
        long gmailEmails = emailsList.stream()
                .filter(email -> email.endsWith("gmail.com"))
                .count();
        long yandexEmails = emailsList.stream()
                .filter(email -> email.endsWith("yandex.ru"))
                .count();
        long hotmailEmails = emailsList.stream()
                .filter(email -> email.endsWith("hotmail.com"))
                .count();
        return (gmailEmails + yandexEmails + hotmailEmails);
    }
}

// END
