package com.shinshinjiru.discord.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class NHentaiComment {
    public int id;
    public Poster poster;
    public String body;

    @JsonProperty("post_date")
    public Timestamp postDate;

    @JsonProperty("gallery_id")
    public int galleryId;

    @Data
    public static class Poster {
        public String username;
        public String slug;

        @JsonProperty("avatar_url")
        public String avatarUrl;

        @JsonProperty("is_superuser")
        public boolean isSuperuser;

        @JsonProperty("is_staff")
        public boolean isStaff;
    }
}
