package com.manulaiko.shinshinjiru.discord.config;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.jagrosh.jdautilities.examples.command.*;
import lombok.Data;
import net.dv8tion.jda.api.OnlineStatus;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.util.List;

/**
 * @author manulaiko <manulaiko@gmail.com>
 */
@Configuration
@ConfigurationProperties("bot")
@Data
public class BotConfiguration {
    private final EventWaiter eventWaiter;

    private String prefix;
    private String success;
    private String warning;
    private String error;
    private int cache;
    private OnlineStatus status;
    private String ownerId;

    private List<Command> commands;

    @PostConstruct
    private void init() {
        commands = List.of(
                new AboutCommand(
                        Color.CYAN,
                        "Shinshinjiru's discord bot",
                        new String[] {"Sync your list", "Find sauce (stop asking, pls)"}
                ),
                new GuildlistCommand(eventWaiter),
                new PingCommand(),
                new RoleinfoCommand(),
                new ServerinfoCommand(),
                new ShutdownCommand()
        );
    }
}