package Task3;

import org.example.Task3.Transliterator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransliteratorTest {

    @ParameterizedTest
    @CsvSource({
          "a, а",
          "yo, ё",
          "zh, ж",
          "shh, щ",
          "y`, ы",
          "e`, э"
    })
    void singleLetters(String latin, String cyrillic) {
        assertEquals(cyrillic, Transliterator.toCyrillic(latin));
        assertEquals(latin, Transliterator.toLatin(cyrillic));
    }

    @ParameterizedTest
    @CsvSource({
            "Ya, Я",
            "Yu, Ю",
            "Zh, Ж",
            "Shh, Щ"
    })
    void casePreservation(String latin, String cyrillic) {
        assertEquals(cyrillic, Transliterator.toCyrillic(latin));
        assertEquals(latin, Transliterator.toLatin(cyrillic));
    }

    @ParameterizedTest
    @CsvSource({
            "Privet, Привет",
            "Yabloko, Яблоко",
            "shhuka, щука",
            "S``ezd, Съезд"
    })
    void wordTransliterate(String latin, String cyrillic) {
        assertEquals(cyrillic, Transliterator.toCyrillic(latin));
        assertEquals(latin, Transliterator.toLatin(cyrillic));
    }

    @Test
    void testBigText() {
        String text =
                "Текст — зафиксированная на каком-либо материальном носителе человеческая мысль;" +
                        "в общем плане связная и полная последовательность символов.\n" +
                        "Существуют две основные трактовки понятия «текст»: " +
                        "имманентная (расширенная, философски нагруженная) " +
                        "и репрезентативная (более частная). " +
                        "Имманентный подход подразумевает отношение к тексту как к автономной " +
                        "реальности, нацеленность на выявление его внутренней структуры. " +
                        "Репрезентативный — рассмотрение текста как особой формы представления " +
                        "информации о внешней тексту действительности.";
        assertEquals(text, Transliterator.toCyrillic(
                Transliterator.toLatin(text)
        ));
    }
}
