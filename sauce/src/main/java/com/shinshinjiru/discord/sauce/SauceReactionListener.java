package com.shinshinjiru.discord.sauce;

import com.shinshinjiru.discord.sauce.SauceCommand;
import com.shinshinjiru.discord.commons.util.MessageUtils;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Sauce reaction listener.
 * ========================
 *
 * Listenes for spagghet reactions to return sauce.
 *
 * @author manulaiko <manulaiko@gmail.com>
 */
@Component
@RequiredArgsConstructor
public class SauceReactionListener extends ListenerAdapter {
    @Value("${discord.emoji.spaghetti}")
    private String spaghet;

    private final SauceCommand sauce;
    private final MessageUtils u;

    /**
     * Handles the received event.
     */
    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        var reaction = event.getReaction()
                .getEmoji()
                .getAsReactionCode();
        if (!reaction.equalsIgnoreCase(spaghet)) {
            return;
        }

        try {
            u.reply(event, sauce.getSauce(u.getMessage(event)));
        } catch (Exception e) {
            u.replyError(event, e.getMessage());
        }
    }
}
