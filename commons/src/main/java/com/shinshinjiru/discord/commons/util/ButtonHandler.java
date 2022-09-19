package com.shinshinjiru.discord.commons.util;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public abstract class ButtonHandler {
    public abstract void handle(ButtonInteractionEvent e);

    public abstract String getCategory();
    public abstract String getId();


    public boolean canHandle(String eventId) {
        return eventId.equalsIgnoreCase(getCategory() + ":"+ getId());
    }
}
