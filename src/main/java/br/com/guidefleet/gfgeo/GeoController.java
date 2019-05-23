package br.com.guidefleet.gfgeo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.geojson.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.stream.IntStream;

@RestController
public class GeoController {

    @GetMapping("/geojson")
    @CrossOrigin
    public String geojson() throws JsonProcessingException {
        FeatureCollection featureCollection = new FeatureCollection();

        Feature feature = new Feature();
        feature.setProperty("key1", "value1");
        feature.setProperty("key2", "value2");

        Geometry<LngLatAlt> geometry = new LineString();

        Random random = new Random();
        // Load thousands of points
        IntStream.range(0, 10000).
                forEach(i -> geometry.add(new LngLatAlt(getRandom(-180, 180), getRandom(-90, 90))));

        feature.setGeometry(geometry);

        featureCollection.add(feature);

        return new ObjectMapper().writeValueAsString(featureCollection);
    }


    private double getRandom(double min, double max) {
        return (Math.random() * ((max - min) + 1)) + min;
    }
}
