package com.richardtaylorindustries.places.service.impl;

import java.io.IOException;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.richardtaylorindustries.places.builder.PlacesRequest;
import com.richardtaylorindustries.places.domain.PlacesResponse;
import com.richardtaylorindustries.places.service.PlacesService;

/**
 * This service implementation utilizes {@link NearbyPlacesDao} to retrieve results
 */
public class PlacesServiceImpl implements PlacesService {

    private static final Logger logger = LoggerFactory.getLogger(PlacesServiceImpl.class);

    private final static HttpTransport HTTP_TRANSPORT = new ApacheHttpTransport();

    @Override
    public PlacesResponse getNearbyPlaces(PlacesRequest placesRequest) {
        Validate.notNull(placesRequest);

        HttpRequestFactory httpRequestFactory = HTTP_TRANSPORT.createRequestFactory();
        PlacesResponse placesResponse = null;
        try {
            HttpRequest httpRequest = httpRequestFactory.buildGetRequest(new GenericUrl(placesRequest
                    .getNEARBY_PLACES_URL()));
            JsonObjectParser jsonObjectParser = new JsonObjectParser(new JacksonFactory());
            httpRequest.setParser(jsonObjectParser);

            /*
             * Required fields
             */
            httpRequest.getUrl().put("key", placesRequest.getApiKey());
            httpRequest.getUrl().put("location", placesRequest.getLatitude() + "," + placesRequest.getLongitude());
            httpRequest.getUrl().put("radius", placesRequest.getRadius());
            httpRequest.getUrl().put("sensor", "false");
            /*
             * Optional fields
             */
            if (null != placesRequest.getPlaceTypes()) {
                String typesString = convertToTypesString(placesRequest);
                httpRequest.getUrl().put("types", typesString.toString());
            }
            if (null != placesRequest.getKeyword()) {
                httpRequest.getUrl().put("keyword", placesRequest.getKeyword());
            }
            if (null != placesRequest.getLanguageCode()) {
                httpRequest.getUrl().put("language", placesRequest.getLanguageCode());
            }
            if (null != placesRequest.getRankBy()) {
                httpRequest.getUrl().put("rankby", placesRequest.getRankBy());
            }

            placesResponse = httpRequest.execute().parseAs(PlacesResponse.class);
            logger.debug("nearby places request url: {}", httpRequest.getUrl().toURL().toString());
            logger.debug("PlacesResponse: {}", placesResponse);
        } catch (IOException e) {
            logger.error("Error occured calling nearby places, {}", e);
        }

        return placesResponse;
    }

    @Override
    public PlacesResponse getNextPage(PlacesRequest placesRequest) {
        Validate.notNull(placesRequest);
        Validate.notEmpty(placesRequest.getNextPageToken());

        HttpRequestFactory httpRequestFactory = HTTP_TRANSPORT.createRequestFactory();
        PlacesResponse placesResponse = null;

        HttpRequest httpRequest;
        try {
            httpRequest = httpRequestFactory.buildGetRequest(new GenericUrl(placesRequest.getNEARBY_PLACES_URL()));
            JsonObjectParser jsonObjectParser = new JsonObjectParser(new JacksonFactory());
            httpRequest.setParser(jsonObjectParser);
            /*
             * Required
             */
            httpRequest.getUrl().put("key", placesRequest.getApiKey());
            httpRequest.getUrl().put("sensor", "false");
            httpRequest.getUrl().put("pagetoken", placesRequest.getNextPageToken());

            placesResponse = httpRequest.execute().parseAs(PlacesResponse.class);
            logger.debug("Nexg page request url: {}", httpRequest.getUrl().toURL().toString());
            logger.debug("Next Page Response: {}", placesResponse);
        } catch (IOException e) {
            logger.error("Error occured calling get next page, {}", e);
        }

        return placesResponse;
    }

    /**
     * The types must be in a list that is pipe (|) delimited ex. type1|type2|type3
     * 
     * @param placesRequest
     * @return
     */
    private String convertToTypesString(PlacesRequest placesRequest) {
        StringBuilder typesString = new StringBuilder();
        for (String type : placesRequest.getPlaceTypes()) {
            typesString.append(type);
            typesString.append('|');
        }
        typesString.deleteCharAt(typesString.length() - 1);
        return typesString.toString();
    }

}
