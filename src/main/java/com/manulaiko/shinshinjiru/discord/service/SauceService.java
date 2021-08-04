package com.manulaiko.shinshinjiru.discord.service;

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequest;
import com.manulaiko.shinshinjiru.discord.api.AniListService;
import com.manulaiko.shinshinjiru.discord.api.TraceMoe;
import com.manulaiko.shinshinjiru.discord.api.model.dto.MediaCoverImageResponseProjection;
import com.manulaiko.shinshinjiru.discord.api.model.dto.MediaQueryRequest;
import com.manulaiko.shinshinjiru.discord.api.model.dto.MediaResponseProjection;
import com.manulaiko.shinshinjiru.discord.api.model.dto.MediaTitleResponseProjection;
import com.manulaiko.shinshinjiru.discord.api.query.Media;
import com.manulaiko.shinshinjiru.discord.exception.NoSauceFoundException;
import lombok.Data;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;

/**
 * Sauce service.
 * ==============
 * <p>
 * Service for finding sauce of images.
 *
 * @author manulaiko <manulaiko@gmail.com>
 */
@Service
@Data
public class SauceService {
    private final TraceMoe traceMoe;
    private final AniListService aniListService;

    @Value("${bot.sauce.similarityThreshold}")
    private double similarityThreshold;

    public MessageEmbed search(String url) {
        var builder = new EmbedBuilder();

        var response = traceMoe.search(url);
        if (response.getResult().size() < 1 || response.getResult().get(0).getSimilarity() < similarityThreshold) {
            throw new NoSauceFoundException();
        }

        var result = response.getResult().get(0);

        var request = new MediaQueryRequest.Builder()
                .setId(result.getAnilist());

        var media = aniListService.query(
                new GraphQLRequest(
                        request.build(),
                        new MediaResponseProjection()
                                .title(
                                        new MediaTitleResponseProjection()
                                                .english()
                                                .userPreferred()
                                )
                                .description()
                                .episodes()
                                .season()
                                .seasonYear()
                                .coverImage(
                                        new MediaCoverImageResponseProjection()
                                                .color()
                                                .medium()
                                )
                ),
                Media.class
        )
                .getData()
                .get("Media");

        return builder.addField("Title", media.getTitle().getUserPreferred(), true)
                .addField("Found at episode", result.getEpisode() + "/"+ media.getEpisodes(), true)
                .addField("Season", media.getSeason().toString().toLowerCase() + " " + media.getSeasonYear(), true)
                .addField("Similarity", String.format("%.2f%%", result.getSimilarity() * 100), true)
                .addField("AniList", "https://anilist.co/anime/" + result.getAnilist(), true)
                .addField("Description", "```\n" + media.getDescription().replaceAll("<br\\s?/?>", "\n") + "\n```", false)
                .setThumbnail(media.getCoverImage().getMedium())
                .setColor(Color.decode(media.getCoverImage().getColor()))
                .build();
    }
}
