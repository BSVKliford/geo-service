package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

public class TestGeoService {
    @Test
    void geoService() {
        GeoService geoService = new GeoServiceImpl();
        Location locationGeoService = geoService.byIp("172.");
        Assertions.assertEquals(Country.RUSSIA, locationGeoService.getCountry());
        locationGeoService = geoService.byIp(GeoServiceImpl.NEW_YORK_IP);
        Assertions.assertEquals(" 10th Avenue", locationGeoService.getStreet());
        locationGeoService = geoService.byIp(GeoServiceImpl.LOCALHOST);
        Assertions.assertEquals(null, locationGeoService.getCountry());
    }
}