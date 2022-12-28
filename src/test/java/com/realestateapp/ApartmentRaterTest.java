package com.realestateapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Executable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentRaterTest {
    @ParameterizedTest(name = "area={0}, price={1}, rating={2}")
    @CsvSource(value = { "72.0, 250000.0, 0", "48.0, 350000.0, 1", "30.0, 600000.0, 2" })
    void rateApartmentTest_Should_ReturnCorrectRating_When_CorrectApartment(double area, double price, int rating) {
        Apartment apartment = new Apartment(area, new BigDecimal(price));
        int actual = ApartmentRater.rateApartment(apartment);
        int expected = rating;

        assertEquals(expected, actual);
    }

    @Test
    void rateApartmentTest_Should_ReturnErrorValue_When_IncorrectApartment() {
        Apartment apartment = new Apartment(0.0, new BigDecimal(300000));
        int expected = -1;
        int actual = ApartmentRater.rateApartment(apartment);

        assertEquals(expected, actual);
    }

    @Test
    void calculateAverageRatingTest_should_CalculateAverageRating_When_CorrectApartmentList() {
        List<Apartment> apartments = new ArrayList<>();
        apartments.add(new Apartment(72.0, new BigDecimal(250000.0)));
        apartments.add(new Apartment(48.0, new BigDecimal(350000.0)));
        apartments.add(new Apartment(30.0, new BigDecimal(600000.0)));

        double actual = ApartmentRater.calculateAverageRating(apartments);
        double expected = 1;

        assertEquals(expected, actual);
    }

    @Test
    void should_ThrowExceptionInCalculateAverageRating_When_EmptyApartmentList(){
        List<Apartment> apartments = new ArrayList<>();
        assertThrows(RuntimeException.class, () -> ApartmentRater.calculateAverageRating(apartments));
    }

}