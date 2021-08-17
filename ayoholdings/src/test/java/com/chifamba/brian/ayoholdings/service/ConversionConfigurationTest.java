package com.chifamba.brian.ayoholdings.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConversionConfigurationTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    private final HttpHeaders httpHeaders = new HttpHeaders();

    @LocalServerPort
    private int port;

    private String createUrlWithPort(final String uri) {
        return "http://localhost:" + port + "/api" + uri;
    }

    @Test
    public void convertUnknownSystem_whenExecuted_thenValidateResponseWithErrorCodeAndMessage() {
        double squareMetre = 91.99;
        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);

        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createUrlWithPort("/test-system/area/" + squareMetre),
                HttpMethod.GET,
                entity,
                String.class);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void convertAcresToMeters_whenExecuted_thenValidateResponse() {
        double acres = 55.2;
        double squareMetre = 223386.67;
        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createUrlWithPort("/metric/area/" + acres),
                HttpMethod.GET,
                entity,
                String.class);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
        assertEquals(String.valueOf(squareMetre), responseEntity.getBody());
    }

    @Test
    public void convertMetersToAcres_whenExecuted_thenValidateResponse() {
        double squareMetre = 223386.67;
        double acres = 55.2;
        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createUrlWithPort("/imperial/area/" + squareMetre),
                HttpMethod.GET,
                entity,
                String.class);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
        assertEquals(String.valueOf(acres), responseEntity.getBody());
    }

    @Test
    public void convertMileToKms_whenExecuted_thenValidateResponse() {
        double mile = 89.52;
        double kilometer = 144.07;
        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createUrlWithPort("/metric/length/" + mile),
                HttpMethod.GET,
                entity,
                String.class);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
        assertEquals(String.valueOf(kilometer), responseEntity.getBody());
    }

    @Test
    public void convertKmsToMiles_whenExecuted_thenValidateResponse() {
        double mile = 89.52;
        double kilometer = 144.07;
        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createUrlWithPort("/imperial/length/" + kilometer),
                HttpMethod.GET,
                entity,
                String.class);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
        assertEquals(String.valueOf(mile), responseEntity.getBody());
    }

    @Test
    public void convertCelsiusToFahrenheit_whenExecuted_thenValidateResponse() {
        double fahrenheit = 105.3;
        double celsius = 40.72;
        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createUrlWithPort("/metric/temperature/" + fahrenheit),
                HttpMethod.GET,
                entity,
                String.class);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
        assertEquals(String.valueOf(celsius), responseEntity.getBody());
    }

    @Test
    public void convertFahrenheitToCelsius_whenExecuted_thenValidateResponse() {
        double celsius = 40.72;
        double fahrenheit = 105.3;
        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createUrlWithPort("/imperial/temperature/" + celsius),
                HttpMethod.GET,
                entity,
                String.class);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
        assertEquals(String.valueOf(fahrenheit), responseEntity.getBody());
    }

    @Test
    public void convertCelciusToUnknownMeasurement_whenExecuted_thenValidateResponseWithErrorCodeAndMessage() {
        double celcius = 42;
        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);

        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createUrlWithPort("/imperial/test-measurement/" + celcius),
                HttpMethod.GET,
                entity,
                String.class);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void convertGalonToLitre_whenExecuted_thenValidateResponse() {
        double galon = 25.7;
        double litre = 116.83;
        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createUrlWithPort("/metric/volume/" + galon),
                HttpMethod.GET,
                entity,
                String.class);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
        assertEquals(String.valueOf(litre), responseEntity.getBody());
    }

    @Test
    public void convertLitreToGalon_whenExecuted_thenValidateResponse() {
        double galon = 25.7;
        double litre = 116.83;
        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createUrlWithPort("/imperial/volume/" + litre),
                HttpMethod.GET,
                entity,
                String.class);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
        assertEquals(String.valueOf(galon), responseEntity.getBody());
    }

    @Test
    public void convertPoundToKilogram_whenExecuted_thenValidateResponse() {
        double pound = 15.32;
        double kilogram = 6.95;
        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createUrlWithPort("/metric/weight/" + pound),
                HttpMethod.GET,
                entity,
                String.class);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
        assertEquals(String.valueOf(kilogram), responseEntity.getBody());
    }

    @Test
    public void convertKilogramToPound_whenExecuted_thenValidateResponse() {
        double pound = 15.32;
        double kilogram = 6.95;
        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createUrlWithPort("/imperial/weight/" + kilogram),
                HttpMethod.GET,
                entity,
                String.class);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
        assertEquals(String.valueOf(pound), responseEntity.getBody());
    }
}
