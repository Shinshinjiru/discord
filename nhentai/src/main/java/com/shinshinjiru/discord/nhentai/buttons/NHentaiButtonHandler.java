package com.shinshinjiru.discord.nhentai.buttons;

import com.shinshinjiru.discord.commons.util.ButtonHandler;
import lombok.Data;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import java.util.Objects;
import java.util.regex.Pattern;

@Data
public abstract class NHentaiButtonHandler extends ButtonHandler {
    public final String category = "nhentai";

    public void handle(ButtonInteractionEvent e) {
        var msg = e.getMessage().getEmbeds().get(0);

        var footer = Objects.requireNonNull(msg.getFooter()).getText();
        var regexp = Pattern.compile("Media ID: ([0-9]+), Current Page: ([0-9]+)/([0-9]+)");

        var m = regexp.matcher(footer);
        if (!m.matches()) {
            return;
        }

        var mediaId = Integer.parseInt(m.group(1));
        var currentPage = Integer.parseInt(m.group(2));
        var totalPages = Integer.parseInt(m.group(3));

        handle(e, msg, mediaId, currentPage, totalPages);
    }

    public abstract void handle(ButtonInteractionEvent e, MessageEmbed msg, int mediaId, int currentPage, int totalPages);
}
