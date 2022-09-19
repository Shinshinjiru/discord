package com.shinshinjiru.discord.nhentai;

import com.shinshinjiru.discord.api.NHentai;
import com.shinshinjiru.discord.api.dto.NHentaiMedia;
import com.shinshinjiru.discord.api.dto.NHentaiResult;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.springframework.stereotype.Service;

import java.util.StringJoiner;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NHentaiService {
    private final NHentai api;

    public NHentaiResult query(String query, int page) {
        return api.search(query, page, "popular");
    }

    public NHentaiMedia media(int id) {
        return api.media(id);
    }

    public String page(int mediaId, int page) {
        return String.format("https://i.nhentai.net/galleries/%s/%s.jpg", mediaId, page);
    }

    public String page(NHentaiMedia media, int page) {
        return String.format("https://i.nhentai.net/galleries/%s/%s.%s", media.getMediaId(), page, extension(media.getImages().getPages().get(page - 1)));
    }

    public String thumbnail(NHentaiMedia media) {
        return String.format("https://t.nhentai.net/galleries/%s/thumb.%s", media.getMediaId(), extension(media.getImages().getThumbnail()));
    }

    public String cover(NHentaiMedia media) {
        return String.format("https://t.nhentai.net/galleries/%s/cover.%s", media.getMediaId(), extension(media.getImages().getCover()));
    }

    public String cover(int media) {
        return String.format("https://t.nhentai.net/galleries/%s/cover.jpg", media);
    }

    private String extension(NHentaiMedia.Image image) {
        return switch (image.getT()) {
            case "p" -> "png";
            case "g" -> "gif";
            default -> "jpg";
        };
    }

    public MessageEmbed buildMessage(NHentaiMedia media) {
        var tags = media.getTags().stream().collect(Collectors.groupingBy(NHentaiMedia.Tag::getType));

        var tagsText = new StringBuilder();
        tagsText.append("```\n");
        tags.forEach((k, v) -> {
            tagsText.append(k.substring(0, 1).toUpperCase())
                    .append(k.substring(1))
                    .append(": ");

            var names = v.stream().map(NHentaiMedia.Tag::getName).collect(Collectors.toSet());
            var joiner = new StringJoiner(", ");

            names.forEach(joiner::add);

            tagsText.append(joiner)
                    .append("\n\n");
        });
        tagsText.append("```");

        var builder = new EmbedBuilder();
        builder.setTitle(media.getTitle().getEnglish())
                .setAuthor(String.valueOf(media.getId()))
                .setFooter(String.format("Media ID: %s, Current Page: %d/%d", media.getMediaId(), 0, media.getNumPages()))
                .setImage(cover(media))
                .setThumbnail(thumbnail(media))
                .addField("Pages", String.format("%,d", media.getNumPages()), true)
                .addField("Favorites", String.format("%,d", media.getNumFavorites()), true)
                .addField("Tags", tagsText.toString(), false);

        return builder.build();
    }
}
