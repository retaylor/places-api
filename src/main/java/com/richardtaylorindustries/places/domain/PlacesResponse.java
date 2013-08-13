package com.richardtaylorindustries.places.domain;

import java.util.List;

import com.google.api.client.util.Key;

public class PlacesResponse {

    @Key
    private String status;
    @Key
    private String next_page_token;
    @Key
    private List<Place> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNext_page_token() {
        return next_page_token;
    }

    public void setNext_page_token(String next_page_token) {
        this.next_page_token = next_page_token;
    }

    public List<Place> getResults() {
        return results;
    }

    public void setResults(List<Place> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "PlacesResponse [status=" + status + ", next_page_token=" + next_page_token + ", results=" + results
                + "]";
    }
}
