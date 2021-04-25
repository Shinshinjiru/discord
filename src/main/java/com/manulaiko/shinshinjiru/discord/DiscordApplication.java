package com.manulaiko.shinshinjiru.discord;

import com.manulaiko.shinshinjiru.discord.api.TraceMoe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author manulaiko <manulaiko@gmail.com>
 */
@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class DiscordApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscordApplication.class, args);
    }
}
