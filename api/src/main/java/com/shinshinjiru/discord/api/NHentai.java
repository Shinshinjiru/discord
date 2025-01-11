package com.shinshinjiru.discord.api;

import com.shinshinjiru.discord.api.dto.NHentaiComment;
import com.shinshinjiru.discord.api.dto.NHentaiMedia;
import com.shinshinjiru.discord.api.dto.NHentaiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * NHentai client
 * ==============
 *
 * Feign client for the NHentai API.
 *
 * @author manulaiko <manulaiko@gmail.com>
 */
@FeignClient(name = "nhentai", url = "${api.nhentai}")
public interface NHentai {
    @GetMapping("/galleries/search")
    NHentaiResult search(@RequestParam("query") String query, @RequestParam("page") int page, @RequestParam("sort") String sort);

    @GetMapping("/galleries/tagged")
    NHentaiResult tagged(@RequestParam("tag") String tag, @RequestParam("page") int page, @RequestParam("sort") String sort);

    @GetMapping("/gallery/{id}/related")
    NHentaiResult tagged(@PathVariable("id") int id);

    @GetMapping("/gallery/{id}/comments")
    List<NHentaiComment> comments(@PathVariable("id") int id);

    @GetMapping("/gallery/{id}")
    NHentaiMedia media(@PathVariable("id") int id);
}
