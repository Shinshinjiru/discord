package com.shinshinjiru.discord.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NHentaiResult {
    public List<NHentaiMedia> result;

    @JsonProperty("num_pages")
    public int numPages;

    @JsonProperty("per_page")
    public int perPage;
}
