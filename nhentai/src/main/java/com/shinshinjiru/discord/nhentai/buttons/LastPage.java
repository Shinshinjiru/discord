package com.shinshinjiru.discord.nhentai.buttons;

import com.shinshinjiru.discord.commons.util.MessageUtils;
import com.shinshinjiru.discord.nhentai.NHentaiService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
public class LastPage extends NHentaiButtonHandler {
    private final String id = "last_page";

    private final MessageUtils u;
    private final NHentaiService service;

    @Override
    public void handle(ButtonInteractionEvent e, MessageEmbed msg, int mediaId, int currentPage, int totalPages) {
        currentPage = totalPages;

        var builder = new EmbedBuilder(msg);
        builder.setFooter(String.format("Media ID: %d, Current Page: %d/%d", mediaId, currentPage, totalPages))
                .setImage(service.page(mediaId, currentPage));

        e.editMessageEmbeds(builder.build()).queue();
    }
}
