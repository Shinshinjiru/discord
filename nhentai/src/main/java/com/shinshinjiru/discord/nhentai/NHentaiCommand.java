package com.shinshinjiru.discord.nhentai;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class NHentaiCommand extends Command {
    private final String name = "nhentai";
    private final String help = "You already know what this is";
    private final String arguments = "<id | title | query>";

    private final NHentaiService service;

    @Override
    protected void execute(CommandEvent event) {
        event.reply("Not implemented");
    }
}
