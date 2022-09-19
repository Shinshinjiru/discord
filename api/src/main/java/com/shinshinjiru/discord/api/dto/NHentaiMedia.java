package com.shinshinjiru.discord.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NHentaiMedia {
    private int id;
    private Title title;
    private Images images;
    private String scanlator;
    private List<Tag> tags;

    @JsonProperty("media_id")
    private String mediaId;

    @JsonProperty("num_pages")
    private int numPages;

    @JsonProperty("num_favorites")
    private int numFavorites;

    @Data
    public static class Title {
        private String english;
        private String japanese;
        private String pretty;
    }

    @Data
    public static class Images {
        private List<Image> pages;
        private Image cover;
        private Image thumbnail;
    }

    @Data
    public static class Image {
        private String t;
        private int w;
        private int h;
    }

    @Data
    public static class Tag {
        private int id;
        private String type;
        private String name;
        private String url;
        private int count;
    }
}
