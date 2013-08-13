package com.richardtaylorindustries.places.domain;

import java.util.List;

import com.google.api.client.util.Key;

public class Photo {

    @Key
    private Integer height;
    @Key
    private Integer width;
    @Key
    private List<String> html_attributions;
    @Key
    private String photo_reference;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public List<String> getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(List<String> html_attributions) {
        this.html_attributions = html_attributions;
    }

    public String getPhoto_reference() {
        return photo_reference;
    }

    public void setPhoto_reference(String photo_reference) {
        this.photo_reference = photo_reference;
    }

    @Override
    public String toString() {
        return "Photo [heigh=" + height + ", width=" + width + ", html_attributions=" + html_attributions
                + ", photo_reference=" + photo_reference + "]";
    }
}
