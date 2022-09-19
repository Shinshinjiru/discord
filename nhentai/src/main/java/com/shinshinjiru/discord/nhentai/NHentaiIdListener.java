package com.shinshinjiru.discord.nhentai;

import com.shinshinjiru.discord.commons.util.MessageUtils;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class NHentaiIdListener extends ListenerAdapter {
    private final MessageUtils u;
    private final NHentaiService service;

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        var msg = event.getMessage().getContentRaw();
        if (!msg.matches("[0-9]{6}")) {
            return;
        }

        var media = service.media(Integer.parseInt(msg));

        var buttons = new ArrayList<Button>();
        buttons.add(Button.primary("nhentai:first_page", Emoji.fromUnicode("⏪")));
        buttons.add(Button.primary("nhentai:previous_page", Emoji.fromUnicode("◀")));
        buttons.add(Button.danger("nhentai:cancel", Emoji.fromUnicode("❌")));
        buttons.add(Button.primary("nhentai:next_page", Emoji.fromUnicode("▶")));
        buttons.add(Button.primary("nhentai:last_page", Emoji.fromUnicode("⏩")));

        u.reply(event, service.buildMessage(media), buttons);
    }
}
