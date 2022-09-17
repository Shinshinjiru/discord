package com.shinshinjiru.discord.commons.util;

import com.jagrosh.jdautilities.command.CommandClient;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import org.springframework.stereotype.Component;

/**
 * Message utils.
 * ==============
 *
 * Class with boilerplate functions for JDA messy event system.
 *
 * @author manulaiko <manulaiko@gmail.com>
 */
@Component
@RequiredArgsConstructor
public class MessageUtils {
    private final CommandClient client;

    public void reply(GenericMessageEvent event, MessageEmbed message) {
        event.getChannel().sendMessageEmbeds(message).queue();
    }

    public void reply(GenericMessageEvent event, String message) {
        event.getChannel().sendMessage(message).queue();
    }

    public void replyError(GenericMessageEvent event, String message) {
        reply(event, client.getError() + " " + message);
    }

    public Message getMessage(GenericMessageEvent event) {
        return event.getChannel().retrieveMessageById(event.getMessageId()).complete();
    }
}
