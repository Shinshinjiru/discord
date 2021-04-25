package com.manulaiko.shinshinjiru.discord.service;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.manulaiko.shinshinjiru.discord.api.TraceMoe;
import com.manulaiko.shinshinjiru.discord.exception.NoSauceFoundException;
import lombok.Data;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Sauce service.
 * ==============
 *
 * Service for finding sauce of images.
 *
 * @author manulaiko <manulaiko@gmail.com>
 */
@Service
@Data
public class SauceService {
    private final TraceMoe traceMoe;

    @Value("${bot.sauce.similarityThreshold}")
    private double similarityThreshold;

    public MessageEmbed search(String url) {
        var builder = new EmbedBuilder();

        var response = traceMoe.search(url);
        if (response.getDocs().size() < 1 || response.getDocs().get(0).getSimilarity() < similarityThreshold) {
            throw new NoSauceFoundException();
        }

        var result = response.getDocs().get(0);

        return builder.addField("Title", result.getTitleRomaji(), true)
                .addField("Episode", result.getEpisode() + "/?", true)
                .addField("Season", result.getSeason(), true)
                .addField("Similarity", String.format("%.2f%%", result.getSimilarity() * 100), true)
                .addField("AniList", "https://anilist.co/anime/"+ result.getAnilistId(), true)
                .addField("Description", "```\nTODO\n```", false)
                .build();
    }
}
