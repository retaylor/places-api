package com.richardtaylorindustries.places.builder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Contains all the information necessary to make a "nearby places" request.
 * 
 * The only required fields are apiKey, latitude and longitude. Radius is default to 50000 meters.
 * 
 * For more information {@link https://developers.google.com/places/documentation/search}
 */
public final class PlacesRequest {

    private final String NEARBY_PLACES_URL;
    private final String apiKey;
    private final Double latitude;
    private final Double longitude;
    private final Double radius;
    private final List<String> placeTypes;
    private final String keyword;
    private final String languageCode;
    private final String rankBy;
    private final String nextPageToken;

    private PlacesRequest(Builder builder) {

        this.NEARBY_PLACES_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
        this.apiKey = builder.apiKey;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.radius = builder.radius;
        this.placeTypes = builder.placeTypes == null ? null : Collections.unmodifiableList(Arrays
                .asList(builder.placeTypes));
        this.keyword = builder.keyword;
        this.languageCode = builder.languageCode;
        this.rankBy = builder.rankBy;
        this.nextPageToken = builder.nextPageToken;
    }

    public static class Builder {

        private String apiKey;
        private Double latitude;
        private Double longitude;
        private Double radius;
        private String[] placeTypes;
        private String keyword;
        private String languageCode;
        private String rankBy;
        private String nextPageToken;

        /**
         * Initializes a Builder with api key
         * 
         * @param apiKey
         */
        public Builder(String apiKey) {
            this.apiKey = apiKey;
        }

        /**
         * Required for nearby places request
         * 
         * @param latitude
         * @return
         */
        public Builder latitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        /**
         * Required for nearby places request
         * 
         * @param longitude
         * @return
         */
        public Builder longitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        /**
         * Optional radius parameter, defaults to 50000 meters.
         * 
         * @param radius
         *            the radius to search in meters
         * @return
         */
        public Builder radius(Double radius) {
            this.radius = radius;
            return this;
        }

        /**
         * Optional type of places to search to see a full list view
         * 
         * {@link https://developers.google.com/places/documentation/supported_types}
         * 
         * @param placeTypes
         * @return
         */
        public Builder placeTypes(String... placeTypes) {
            this.placeTypes = placeTypes;
            return this;
        }

        /**
         * Optional keyword
         * 
         * @param keyword
         * @return
         */
        public Builder keyword(String keyword) {
            this.keyword = keyword;
            return this;
        }

        /**
         * Optional lanague code, to see all lanuges supported view
         * 
         * {@link https://spreadsheets.google.com/pub?key=p9pdwsai2hDMsLkXsoM05KQ&gid=1}
         * 
         * @param languageCode
         * @return
         */
        public Builder language(String languageCode) {
            this.languageCode = languageCode;
            return this;
        }

        /**
         * Optional rankBy may be {@code prominence } or {@code distance}.
         * 
         * If {@code distance} is used, then keyword, name, or types are required
         * 
         * @param rankBy
         * @return
         */
        public Builder rankBy(String rankBy) {
            this.rankBy = rankBy;
            return this;
        }

        /**
         * Supply a next page token to retrieve the next set of results, having this token will ignore all other
         * parameters
         * 
         * @param nextPageToken
         * @return
         */
        public Builder nextPageToken(String nextPageToken) {
            this.nextPageToken = nextPageToken;
            return this;
        }

        /**
         * Creates a mutible PlacesRequest object from this {@link Builder}.
         * 
         * @return
         */
        public PlacesRequest build() {
            return new PlacesRequest(this);
        }

    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getRadius() {
        return radius;
    }

    public List<String> getPlaceTypes() {
        return placeTypes;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String getRankBy() {
        return rankBy;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getNEARBY_PLACES_URL() {
        return NEARBY_PLACES_URL;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    @Override
    public String toString() {
        return "PlacesRequest [NEARBY_PLACES_URL=" + NEARBY_PLACES_URL + ", apiKey=" + apiKey + ", latitude="
                + latitude + ", longitude=" + longitude + ", radius=" + radius + ", placeTypes=" + placeTypes
                + ", keyword=" + keyword + ", languageCode=" + languageCode + ", rankBy=" + rankBy + ", nextPageToken="
                + nextPageToken + "]";
    }
}
