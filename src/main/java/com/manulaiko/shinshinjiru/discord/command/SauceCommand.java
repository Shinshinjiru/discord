package com.manulaiko.shinshinjiru.discord.command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.manulaiko.shinshinjiru.discord.exception.NoSauceFoundException;
import com.manulaiko.shinshinjiru.discord.service.SauceService;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
        if (event.getMessage().getAttachments().isEmpty()) {
            event.replyError("Attach an image ffs");

            return;
        }

        if (event.getMessage().getAttachments().size() > 1) {
            event.replyError("Just one, pls, don't make this more complicated than necessary");

            return;
        }

        var img = event.getMessage().getAttachments().get(0);

        if (!img.isImage()) {
            event.replyError("Pls, image, image, IMAGE");

            return;
        }

        try {
            event.reply(service.search(img.getUrl()));
        } catch(NoSauceFoundException e) {
            event.replyError("No sauce found, better luck next time :)");
        } catch (Exception e) {
            event.replyError("Couldn't find sauce\n```"+ e.getMessage() +"```");
        }
    }
}
