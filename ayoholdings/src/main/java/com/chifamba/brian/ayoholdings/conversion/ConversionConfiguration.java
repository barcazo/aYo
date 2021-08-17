package com.chifamba.brian.ayoholdings.conversion;

import com.chifamba.brian.ayoholdings.model.UnitType;
import com.chifamba.brian.ayoholdings.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import static java.lang.String.format;

/**
 * The Conversion configuration will set attributes related to conversion.
 */
@Component
public class ConversionConfiguration {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final AreaService areaService;
    private final LengthService lengthService;
    private final TemperatureService temperatureService;
    private final VolumeService volumeService;
    private final WeightService weightService;

    public ConversionConfiguration(AreaService areaService,
                                   LengthService lengthService,
                                   TemperatureService temperatureService,
                                   VolumeService volumeService,
                                   WeightService weightService) {
        this.areaService = areaService;
        this.lengthService = lengthService;
        this.temperatureService = temperatureService;
        this.volumeService = volumeService;
        this.weightService = weightService;
    }


    public ConverterFactory getConversionService(UnitType unitType) {
        log.info("Conversion configuration will set attributes related to conversion service");
        switch(unitType){
            case AREA: return areaService;
            case LENGTH: return lengthService;
            case TEMPERATURE: return temperatureService;
            case VOLUME: return volumeService;
            case WEIGHT: return weightService;
            default:
                throw new RuntimeException(format("No implementation for this conversion type [%s]", unitType.name()));
        }
    }
}
