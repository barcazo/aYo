package com.chifamba.brian.ayoholdings.service;

import com.chifamba.brian.ayoholdings.Exception.IncompatibleUnitTypesException;
import com.chifamba.brian.ayoholdings.conversion.ConversionConfiguration;
import com.chifamba.brian.ayoholdings.conversion.ConverterFactory;
import com.chifamba.brian.ayoholdings.model.UnitSystem;
import com.chifamba.brian.ayoholdings.model.UnitType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.chifamba.brian.ayoholdings.model.UnitSystem.IMPERIAL;
import static com.chifamba.brian.ayoholdings.model.UnitSystem.METRIC;


@Service
public class ConversionService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ConversionConfiguration conversionConfiguration;

        /**
         * To convert <code>inputValue</code> of any known type (<code>ConversionType</code>) of measurements between
         * imperial and metric based on the type of system.
         *
         * Sets type via String parameter, associated with a predefined set of keywords.
         *
         * @param unitType the conversion type
         * @param unitSystem the system type
         * @param value the input value to be converted
         * @throws IllegalStateException the illegal state exception when input is bad
         */
        public ResponseEntity<String> convert(String unitSystem, String unitType, Double value)
                throws IllegalArgumentException {
            Double convertedValue;
            HttpStatus status;

            if (StringUtils.isAnyBlank(unitSystem, unitType, String.valueOf(value)))
                {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }else
                {
                try {
                    ConverterFactory converterFactory = this.getConverter(unitType);
                    log.info("Determining whether its a metric or empirical translation");
                    switch (this.getSystemToConvertInto(unitSystem.toUpperCase())) {
                        case METRIC:
                            convertedValue = converterFactory.metricalConversion(value);
                            break;
                        case IMPERIAL:
                            convertedValue = converterFactory.imperialConversion(value);
                            break;
                        default:
                            throw new RuntimeException("Unknown System, the accepted values are "  +
                                    METRIC.name() + " and " + IMPERIAL.name());
                    }
                    status = HttpStatus.OK;
                } catch (IllegalArgumentException | IncompatibleUnitTypesException e) {
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<>(String.valueOf(convertedValue), status);
        }

        private ConverterFactory getConverter(String unitType) throws IncompatibleUnitTypesException {
            ConverterFactory converterFactory;
            try {
                log.debug("UnitType:" +UnitType.valueOf(unitType.toUpperCase()));
                converterFactory = conversionConfiguration.getConversionService(UnitType.valueOf(unitType.toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new IncompatibleUnitTypesException("No implementation for the specified type. " +
                        "Currently these are the only accepted types such as: [" +
                        Arrays.stream(UnitType.values()).map(UnitType::name)
                                .collect(Collectors.joining(", ")) + "]");
            }

            log.debug("Converter value: "+ converterFactory.toString());
            return converterFactory;
        }

        private UnitSystem getSystemToConvertInto(final String s) throws IllegalArgumentException {
            UnitSystem systemType;

            try {
                log.debug("getSystemToConvertInto" +UnitSystem.valueOf(s.toUpperCase()));
                systemType = UnitSystem.valueOf(s.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("You may only specify a known system such as ["+UnitSystem.METRIC.name()+"] or ["+UnitSystem.IMPERIAL.name()+"]");
            }

            return systemType;
        }

        public ResponseEntity<List<String>> getMeasurements() {
            return new ResponseEntity<>(Arrays.stream(UnitType.values()).map(UnitType::name).collect(Collectors.toList()), HttpStatus.OK);
        }

        public ResponseEntity<List<String>> getSiUnits() {
            return new ResponseEntity<>(Arrays.stream(UnitSystem.values()).map(UnitSystem::name).collect(Collectors.toList()), HttpStatus.OK);
        }
}
