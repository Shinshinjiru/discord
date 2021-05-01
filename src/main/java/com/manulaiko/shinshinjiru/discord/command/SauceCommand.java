package com.manulaiko.shinshinjiru.discord.command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.manulaiko.shinshinjiru.discord.exception.NoSauceFoundException;
import com.manulaiko.shinshinjiru.discord.service.SauceService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.springframework.stereotype.Component;

/**
 * Sauce command.
 * ==============
 *
 * Finds the sauce of the attached image.
 *
 * @author manulaiko <manulaiko@gmail.com>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Component
public class SauceCommand extends Command {
    private final SauceService service;

    private String name = "sauce";
    private String help = "Finds the sauce of the attached image";
    private boolean guildOnly = false;

    /**
     * The main body method of a {@link Command Command}.
     * <br>This is the "response" for a successful
     * {@link Command#run(CommandEvent) #run(CommandEvent)}.
     *
     * @param event The {@link CommandEvent CommandEvent} that
     *              triggered this Command
     */
    @Override
    protected void execute(CommandEvent event) {
        try {
            event.reply(getSauce(event.getMessage()));
        } catch (Exception e) {
            event.replyError(e.getMessage());
        }
    }

    public MessageEmbed getSauce(Message message) {
        if (message.getAttachments().isEmpty()) {
            throw new IllegalArgumentException("Attach an image ffs");
        }

        if (message.getAttachments().size() > 1) {
            throw new IllegalArgumentException("Just one, pls, don't make this more complicated than necessary");
        }

        var img = message.getAttachments().get(0);

        if (!img.isImage()) {
            throw new IllegalArgumentException("Pls, image, image, IMAGE");
        }

        try {
            return service.search(img.getUrl());
        } catch(NoSauceFoundException e) {
            throw new IllegalArgumentException("No sauce found, better luck next time :)");
        } catch (Exception e) {
            throw new IllegalArgumentException("Couldn't find sauce\n```"+ e.getMessage() +"```");
        }
    }
}
