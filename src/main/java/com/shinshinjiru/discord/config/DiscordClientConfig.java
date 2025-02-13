package com.shinshinjiru.discord.config;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author manulaiko <manulaiko@gmail.com>
 */
@Configuration
public class DiscordClientConfig {
    @Value("${discord.token}")
    private String token;

    @Bean
    public EventWaiter eventWaiter() {
        return new EventWaiter();
    }

    @Bean
    public CommandClient commandClient(BotConfiguration config) {
        var builder = new CommandClientBuilder()
                .setPrefix(config.getPrefix())
                .setOwnerId(config.getOwnerId())
                .setEmojis(config.getSuccess(), config.getWarning(), config.getError())
                .setLinkedCacheSize(config.getCache())
                .setStatus(config.getStatus());

        config.getCommands().forEach(builder::addCommand);

        return builder.build();
    }

    @Bean
    public JDA jda(CommandClient client, EventWaiter eventWaiter, List<? extends ListenerAdapter> listeners, BotConfiguration config) {
        var builder = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .enableCache(CacheFlag.MEMBER_OVERRIDES)
                .disableCache(CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.EMOJI)
                .setActivity(Activity.playing("loading..."))
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setBulkDeleteSplittingEnabled(true)
                .addEventListeners(new ListenerAdapter() {
                    @Override
                    public void onReady(ReadyEvent event) {
                        event.getJDA().getPresence().setStatus(config.getStatus());
                    }
                }, client);

        listeners.forEach(builder::addEventListeners);

        return builder.build();
    }
}
