package com.richardtaylorindustries.places.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.richardtaylorindustries.places.builder.PlacesRequest;
import com.richardtaylorindustries.places.domain.PlacesResponse;

public class PlacesServiceImplTest {

    private PlacesServiceImpl placesServiceImpl;

    @Before
    public void setup() {
        placesServiceImpl = new PlacesServiceImpl();
    }

    @Test
    public void testGetNearbyPlaces() {
        PlacesRequest placesRequest = new PlacesRequest.Builder("AIzaSyDLaLrDZJweaGPu3ptQgVATaBO3Ei4gUcU")
                .latitude(35.2318290).longitude(-80.8454550).radius((double) 5000).placeTypes("restaurant").build();
        PlacesResponse placesResponse = placesServiceImpl.getNearbyPlaces(placesRequest);
        assertNotNull(placesResponse);
        assertEquals("OK", placesResponse.getStatus());
        assertNotNull(placesResponse.getResults());
    }

    @Test
    public void testGetNearbyPlacesBadApiKey() {
        PlacesRequest placesRequest = new PlacesRequest.Builder("999").latitude(35.2318290).longitude(-80.8454550)
                .radius((double) 5000).placeTypes("restaurant").build();
        PlacesResponse placesResponse = placesServiceImpl.getNearbyPlaces(placesRequest);
        assertNotNull(placesResponse);
        assertEquals("REQUEST_DENIED", placesResponse.getStatus());
        assertNotNull(placesResponse.getResults());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNearbyPlacesNullRequest() {
        PlacesResponse placesResponse = placesServiceImpl.getNearbyPlaces(null);
        assertNull(placesResponse);
    }

    @Test
    public void testGetNextPage() {
        PlacesRequest placesRequest = new PlacesRequest.Builder("AIzaSyDLaLrDZJweaGPu3ptQgVATaBO3Ei4gUcU")
                .latitude(35.2318290).longitude(-80.8454550).radius((double) 5000).placeTypes("restaurant").build();
        PlacesResponse placesResponse = placesServiceImpl.getNearbyPlaces(placesRequest);
        String nextToken = placesResponse.getNext_page_token();
        PlacesRequest placesRequestNextPage = new PlacesRequest.Builder("AIzaSyDLaLrDZJweaGPu3ptQgVATaBO3Ei4gUcU")
                .nextPageToken(nextToken).build();
        try {
            /*
             * Need 3 seconds of sleep between queries
             */
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // nothing
        }
        PlacesResponse placesResponseNextPage = placesServiceImpl.getNextPage(placesRequestNextPage);
        assertNotNull(placesResponseNextPage);
        assertEquals("OK", placesResponseNextPage.getStatus());
        assertNotNull(placesResponseNextPage.getResults());
    }

}
