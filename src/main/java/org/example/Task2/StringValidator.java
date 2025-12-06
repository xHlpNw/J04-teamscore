package org.example.Task2;

import java.util.regex.Pattern;

public class StringValidator {
/*
    Реализуйте статический метод, в котором с помощью регулярных выражений проверит,
    чем является переданная строка текста:
    •	адресом email;
    •	номером телефона в формате +7-(000)-000-00-00, дефисы и скобки необязательны;
    •	номером ИНН (10 или 12 цифр без разделителей);
    •	именем пользователя (не менее 8 символов, может содержать латинские буквы,
        цифры, символы '_' '$' '.', начинаться может только с буквы);
*/

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    // +7-(000)-000-00-00, дефисы и скобки необязательны;
    private static final String PHONE_REGEX =
            "^\\+7[-\\s]?(\\(\\d{3}\\)|\\d{3})[-\\s]?\\d{3}[-\\s]?\\d{2}[-\\s]?\\d{2}$";

    // номер ИНН (10 или 12 цифр без разделителей)
    private static final String TIN_REGEX = "^\\d{10}|\\d{12}$";

    // имя пользователя (не менее 8 символов, может содержать латинские буквы,
    // цифры, символы '_' '$' '.', начинаться может только с буквы);
    private static final String USERNAME_REGEX = "[a-zA-Z][a-zA-Z0-9_$.]{7,}";

    public static String validate(String line) {
        String trimmed = line.trim();

        if (Pattern.matches(EMAIL_REGEX, trimmed)) return "email";
        if (Pattern.matches(PHONE_REGEX, trimmed)) return "телефон";
        if (Pattern.matches(TIN_REGEX, trimmed)) return "ИНН";
        if (Pattern.matches(USERNAME_REGEX, trimmed)) return "username";

        return "none";
    }
}
