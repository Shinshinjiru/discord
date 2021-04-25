package com.manulaiko.shinshinjiru.discord.listener;

import lombok.Data;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Bridge event listener.
 * ======================
 * <p>
 * Receives the events from the JDA event bus and publishes them on Spring's event bus.
 *
 * @author manulaiko <manulaiko@gmail.com>
 */
@Component
@Data
public class BridgeEventListener implements EventListener {
    private final ApplicationContext ctx;

    /**
     * Handles any {@link GenericEvent GenericEvent}.
     *
     * <p>To get specific events with Methods like {@code onMessageReceived(MessageReceivedEvent event)}
     * take a look at: {@link ListenerAdapter ListenerAdapter}
     *
     * @param event The Event to handle.
     */
    @Override
    public void onEvent(@NotNull GenericEvent event) {
        ctx.publishEvent(event);
    }
}
