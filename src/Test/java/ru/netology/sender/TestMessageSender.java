package ru.netology.sender;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMessageSender {
    @ParameterizedTest
    @MethodSource("value")
    void Send(String ip, String expected, Location location) {
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.startsWith(ip))).thenReturn(location);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(location.getCountry())).thenReturn(expected);
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String mess = messageSender.send(headers);
        assertEquals(expected, mess);
    }

    public static Stream<Arguments> value() {
        return Stream.of(Arguments.of("172.0.32.11", "Добро пожаловать", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.44.183.149", "Welcome", new Location("New York", Country.USA, null, 0)));
    }
}