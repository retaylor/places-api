package com.richardtaylorindustries.places.domain;

import java.util.List;

import com.google.api.client.util.Key;

public class Place {

    @Key
    private String icon;
    @Key
    private String id;
    @Key
    private String name;
    @Key
    private List<Photo> photos;
    @Key
    private Integer price_level;
    @Key
    private Double rating;
    @Key
    private String reference;
    @Key
    private List<String> types;
    @Key
    private String vicinity;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Integer getPrice_level() {
        return price_level;
    }

    public void setPrice_level(Integer price_leve) {
        this.price_level = price_leve;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    @Override
    public String toString() {
        return "Place [icon=" + icon + ", id=" + id + ", name=" + name + ", photos=" + photos + ", price_level="
                + price_level + ", rating=" + rating + ", reference=" + reference + ", types=" + types + ", vicinity="
                + vicinity + "]";
    }
}
