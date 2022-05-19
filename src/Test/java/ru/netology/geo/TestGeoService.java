package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

public class TestGeoService {
    @ParameterizedTest
    @MethodSource("value")
    void geoService(String ip, Location location) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location actual = geoService.byIp(ip);
        Assertions.assertEquals(location.getCity(), actual.getCity());
        Assertions.assertEquals(location.getCountry(), actual.getCountry());
    }

    public static Stream<Arguments> value() {
        return Stream.of(Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("127.0.0.1", new Location(null, null, null, 0)));
    }
}