package com.chifamba.brian.ayoholdings.controller;

import com.chifamba.brian.ayoholdings.service.ConversionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ConversionController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ConversionService conversionService;

    /**
     * Custom converter response entity. This receives the system measurement, the type and numerical value to convert.
     *
     * @param system   the measurement
     * @param type the type
     * @param value input amount to be converted
     * @return the response entity
     */

    @GetMapping(value = "/{system}/{type}/{value}")
    public ResponseEntity<String> convert(@PathVariable String system,
                                          @PathVariable String type,
                                          @PathVariable  Double value) {
        log.debug("System :"+system+" Type :"+type+" Input :"+value);
        return conversionService.convert(system, type, value);
    }
}
