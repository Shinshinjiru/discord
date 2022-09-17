package com.shinshinjiru.discord.api;

import com.shinshinjiru.discord.api.dto.TraceMoeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Trace.moe client.
 * =================
 * <p>
 * Feign client for the trace.moe api.
 *
 * @author manulaiko <manulaiko@gmail.com>
 */
@FeignClient(name = "tracemoe", url = "${api.tracemoe}")
public interface TraceMoe {
    @GetMapping("/search")
    TraceMoeResponse search(@RequestParam("url") String url);
}
