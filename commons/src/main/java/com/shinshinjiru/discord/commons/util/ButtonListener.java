package com.shinshinjiru.discord.commons.util;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ButtonListener extends ListenerAdapter {
    private final List<? extends ButtonHandler> handlers;

    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        handlers.stream()
                .filter(h -> h.canHandle(event.getButton().getId()))
                .findFirst()
                .ifPresent(h -> h.handle(event));
    }
}
