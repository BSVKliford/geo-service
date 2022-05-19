package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.stream.Stream;

public class TestLocation {
    @ParameterizedTest
    @MethodSource("value")
    void locationService(String expected, Country country) {
        String count = new LocalizationServiceImpl().locale(country);
        Assertions.assertEquals(expected, count);
    }

    public static Stream<Arguments> value() {
        return Stream.of(Arguments.of("Добро пожаловать", Country.RUSSIA),
                Arguments.of("Welcome", Country.USA));
    }
}