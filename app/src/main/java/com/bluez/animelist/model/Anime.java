package com.bluez.animelist.model;

/**
 * Created by bluez on 22/6/18.
 */

public class Anime {
    private String name;
    private String description;
    private String rating;
    private int episodes;
    private String category;
    private String studio;
    private String imgUrl;

    public Anime() {}

    public Anime(String name, String description, String rating, int episodes, String category, String studio, String imgUrl) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.episodes = episodes;
        this.category = category;
        this.studio = studio;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRating() {
        return rating;
    }

    public int getEpisodes() {
        return episodes;
    }

    public String getCategory() {
        return category;
    }

    public String getStudio() {
        return studio;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
