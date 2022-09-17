package com.shinshinjiru.discord.util;

import com.shinshinjiru.discord.config.BotConfiguration;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Activity changer.
 * =================
 *
 * Constantly loops through the available activities texts.
 *
 * @author manulaiko <manulaiko@gmail.com>
 */
@Component
@Data
@Slf4j
public class ActivityChanger {
    private final BotConfiguration configuration;
    private final JDA jda;

    @Scheduled(initialDelay = 10000, fixedDelay = 3600000)
    public void changeActivity() {
        var list = configuration.getActivityStatus();
        var activity = list.get(ThreadLocalRandom.current().nextInt(list.size()));

        jda.getPresence().setActivity(Activity.of(activity.getType(), activity.getText()));
    }
}
