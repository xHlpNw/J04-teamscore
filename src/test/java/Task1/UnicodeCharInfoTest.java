package Task1;

import org.example.Task1.UnicodeCharInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnicodeCharInfoTest {
    @ParameterizedTest
    @CsvSource({
            "'0', 48",
            "'N', 78",
            "'z', 122",
            "'Ж', 1046",
            "' ', 32",
            "'\t', 9",
            "'&', 38",
            "'λ', 955"
    })
    void testGetUnicode(char symbol, int code) {
        UnicodeCharInfo info = new UnicodeCharInfo(symbol);
        assertEquals(code, info.getUnicode());
    }

    @ParameterizedTest
    @CsvSource({
            "'0', U+0030",
            "'N', U+004E",
            "'z', U+007A",
            "'Ж', U+0416",
            "' ', U+0020",
            "'\t', U+0009",
            "'&', U+0026",
            "'λ', U+03BB"
    })
    void testGetUnicodeHex(char symbol, String hexCode) {
        UnicodeCharInfo info = new UnicodeCharInfo(symbol);
        assertEquals(hexCode, info.getUnicodeHex());
    }

    @ParameterizedTest
    @CsvSource({
            "'0', '1'",
            "'N', 'O'",
            "'z', '{'",
            "'Ж', 'З'",
            "' ', '!'",
            "'\t', '\n'",
            "'&', ''''",
            "'λ', 'μ'"
    })
    void testNextChar(char symbol, char nextSymbol) {
        UnicodeCharInfo info = new UnicodeCharInfo(symbol);
        assertEquals(nextSymbol, info.nextChar());
    }

    @ParameterizedTest
    @CsvSource({
            "'0', '/'",
            "'N', 'M'",
            "'z', 'y'",
            "'Ж', 'Е'",
            "' ', '\u001F'",
            "'\t', '\b'",
            "'&', '%'",
            "'λ', '\u03BA'"
    })
    void testPreviousChar(char symbol, char prevSymbol) {
        UnicodeCharInfo info = new UnicodeCharInfo(symbol);
        assertEquals(prevSymbol, info.previousChar());
    }

    @ParameterizedTest
    @CsvSource({
            "'0', 1",
            "'N', 2",
            "'z', 3",
            "'Ж', 4",
            "' ', 6",
            "'\t', 6",
            "'&', 0",
            "'λ', 0"
    })
    void testGetType(char symbol, int type) {
        UnicodeCharInfo info = new UnicodeCharInfo(symbol);
        assertEquals(type, info.getType());
    }

    @ParameterizedTest
    @CsvSource({
            "'0', -1",
            "'N', 14",
            "'z', 26",
            "'Ж', -1",
            "' ', -1",
            "'\t', -1",
            "'&', -1",
            "'λ', -1"
    })
    void testGetLetterNumber(char symbol, int number) {
        UnicodeCharInfo info = new UnicodeCharInfo(symbol);
        assertEquals(number, info.getLetterNumber());
    }
}
