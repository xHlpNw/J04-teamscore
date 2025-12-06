package org.example.Task1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UnicodeCharInfo {
    private char symbol;

    /**
     * Константы для типов символа
     */
    public static final int DIGIT = 1;
    public static final int UPPER_LATIN = 2;
    public static final int LOWER_LATIN = 3;
    public static final int UPPER_CYRILLIC = 4;
    public static final int LOWER_CYRILLIC = 5;
    public static final int WHITESPACE = 6;
    public static final int OTHER = 0;

    /**
     * Метод для получения кода символа в Unicode
     * @return целочисленное значение кода символа в Unicode
     */
    public int getUnicode() {
        return symbol;
    }

    /**
     * Метод для получения шестнадцатиричного кода символа в Unicode
     * @return строку с 16-ричным представлением кода символа в Unicode
     */
    public String getUnicodeHex() {
        return String.format("U+%04X", (int) symbol);
    }

    /**
     * Метод для получения следующего в кодировке Unicode символа
     * @return char с символом стоящим в Unicode после данного
     */
    public char nextChar() {
        return (char) (symbol + 1);
    }

    /**
     * Метод для получения предыдущего в кодировке Unicode символа
     * @return char с символом стоящим в Unicode перед данным
     */
    public char previousChar() {
        return (char) (symbol - 1);
    }

    /**
     * Метод для определения типа символа
     * @return целочисленное значение константы типа
     */
    public int getType() {
        if (Character.isDigit(symbol)) return DIGIT;
        if (Character.isWhitespace(symbol)) return WHITESPACE;

        if (symbol >= 'A' && symbol <= 'Z') return UPPER_LATIN;
        if (symbol >= 'a' && symbol <= 'z') return LOWER_LATIN;

        if ((symbol >= 'А' && symbol <= 'Я') || symbol == 'Ё') return UPPER_CYRILLIC;
        if ((symbol >= 'а' && symbol <= 'я') || symbol == 'ё') return LOWER_CYRILLIC;

        return OTHER;
    }

    /**
     * Метод для получения номера латинской буквы в алфавите
     * (не чувствителен к регистру)
     * @return целочисленное значение позиции буквы в алфавите (-1 для небукв)
     */
    public int getLetterNumber() {
        char lowerSymbol = Character.toLowerCase(symbol);
        if (lowerSymbol >= 'a' && lowerSymbol <= 'z') {
            return lowerSymbol - 'a' + 1;
        }
        return -1;
    }
}
