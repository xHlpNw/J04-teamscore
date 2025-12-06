package Task2;

import org.example.Task2.StringValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringValidatorTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "user.name@example.com",
            "user_name1@some.example.com"
    })
    void testEmailValidation(String line) {
        assertEquals("email", StringValidator.validate(line));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "+7-(123)-456-78-90",
            "+7(123)456-78-90",
            "+7-123-456-78-90",
            "  +71234567890  "
    })
    void testPhoneValidation(String line) {
        assertEquals("телефон", StringValidator.validate(line));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1234567890",
            "000000000000"
    })
    void testTinValidation(String line) {
        assertEquals("ИНН", StringValidator.validate(line));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "user.name.example.com",
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.$1234_",
            "\taaaa1111\t"
    })
    void testUsernameValidation(String line) {
        assertEquals("username", StringValidator.validate(line));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "@example.com",
            "user.name@example",
            "71234567890",
            "7777-8888-9999",
            "a",
            "qwerty 456",
            "4abc",
            "$asdfghjk",
            ""
    })
    void testNoneValidation(String line) {
        assertEquals("none", StringValidator.validate(line));
    }
}
