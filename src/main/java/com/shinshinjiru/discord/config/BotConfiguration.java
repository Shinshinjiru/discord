package com.shinshinjiru.discord.config;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.jagrosh.jdautilities.examples.command.*;
import com.shinshinjiru.discord.sauce.SauceCommand;
import com.shinshinjiru.discord.nhentai.NHentaiCommand;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
    private final SauceCommand sauceCommand;
    private final NHentaiCommand nHentaiCommand;

    private String prefix;
    private String success;
    private String warning;
    private String error;
    private int cache;
    private OnlineStatus status;
    private String ownerId;
    private List<Status> activityStatus;

    private List<Command> commands;

    @PostConstruct
    private void init() {
        commands = List.of(
                new AboutCommand(
                        Color.CYAN,
                        "Shinshinjiru's discord bot",
                        new String[] {
                                "Sync your list",
                                "Find sauce (stop asking, pls)",
                                "Search for nhentai nuclear codes"
                        }
                ),
                new GuildlistCommand(eventWaiter),
                new PingCommand(),
                new RoleinfoCommand(),
                new ServerinfoCommand(),
                new ShutdownCommand(),

                sauceCommand,
                nHentaiCommand
        );
    }

    @Data
    public static class Status {
        private Activity.ActivityType type;
        private String text;
    }
}
