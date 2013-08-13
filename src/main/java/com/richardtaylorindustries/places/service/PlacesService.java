package com.richardtaylorindustries.places.service;

import com.richardtaylorindustries.places.builder.PlacesRequest;
import com.richardtaylorindustries.places.domain.PlacesResponse;

/**
 * Service that utilizes the google places API.
 * 
 * For more information {@link https://developers.google.com/places/documentation/search}
 */
public interface PlacesService {

    /**
     * Return a list of nearby places based on the supplied {@link PlacesRequest}
     * 
     * @param placesRequest
     *            the request that contains on mandatory and optional parameters for the nearby places call
     * @return {@link PlacesResponse} or {@code null} if the request will not parse
     */
    PlacesResponse getNearbyPlaces(PlacesRequest placesRequest);

    /**
     * Returns a list of nearby places from the supplied next Page Token
     * 
     * @param placesRequest
     *            places request, the nexg page token must be supplied
     * @return {@link PlacesResponse} or {@code null} if the request will not parse
     */
    PlacesResponse getNextPage(PlacesRequest placesRequest);
}
