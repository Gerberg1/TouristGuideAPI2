package com.example.tourism.model;

import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;

    private String cityPart;

    private List<String> tags;

    public TouristAttraction(String name, String description, String cityPart) {
        this.name = name;
        this.description = description;
        this.cityPart = cityPart;
        this.tags = tags;
    }

    /*public TouristAttraction(String name, String description, String cityPart, List<String> tags) {
        this.name = name;
        this.description = description;
        this.cityPart = cityPart;
        this.tags = tags;
    }*/

    public TouristAttraction(){}

    public <E> TouristAttraction(String guldbar, String fridayBar, String nørrebro, List<E> studentbar) {
    }

    public String getName(){
        return name;
    }

    public String getCityPart() {
        return cityPart;
    }

    public String getDescription (){
        return description;
    }

    public List<String> getTags(){
        return tags;
    }

    public String getAllTags(){
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : tags){
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCityPart(String cityPart){
        this.cityPart = cityPart;
    }

    public void setTags(List<String> tags){
        this.tags=tags;
    }
}
