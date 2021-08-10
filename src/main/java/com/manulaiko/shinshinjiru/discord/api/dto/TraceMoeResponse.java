package com.manulaiko.shinshinjiru.discord.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * TraceMoe response.
 * ==================
 *
 * Response DTO for the tracemoe api.
 *
 * {
 *   "RawDocsCount": 3555648,
 *   "RawDocsSearchTime": 14056,
 *   "ReRankSearchTime": 1182,
 *   "CacheHit": false,
 *   "trial": 1,
 *   "limit": 9,
 *   "limit_ttl": 60,
 *   "quota": 150,
 *   "quota_ttl": 86400,
 *   "docs": [
 *     {
 *       "from": 663.17,
 *       "to": 665.42,
 *       "anilist_id": 98444,
 *       "at": 665.08,
 *       "season": "2018-01",
 *       "anime": "搖曳露營",
 *       "filename": "[Ohys-Raws] Yuru Camp - 05 (AT-X 1280x720 x264 AAC).mp4",
 *       "episode": 5,
 *       "tokenthumb": "bB-8KQuoc6u-1SfzuVnDMw",
 *       "similarity": 0.9563952960290518,
 *       "title": "ゆるキャン△",
 *       "title_native": "ゆるキャン△",
 *       "title_chinese": "搖曳露營",
 *       "title_english": "Laid-Back Camp",
 *       "title_romaji": "Yuru Camp△",
 *       "mal_id": 34798,
 *       "synonyms": ["Yurucamp", "Yurukyan△"],
 *       "synonyms_chinese": [],
 *       "is_adult": false
 *     }
 *   ]
 * }
 * @author manulaiko <manulaiko@gmail.com>
 */
@Data
public class TraceMoeResponse {
    private long frameCount;
    private String error;
    private List<Doc> result;

    /**
     *   "docs": [
     *     {
     *       "from": 663.17,
     *       "to": 665.42,
     *       "anilist_id": 98444,
     *       "at": 665.08,
     *       "season": "2018-01",
     *       "anime": "搖曳露營",
     *       "filename": "[Ohys-Raws] Yuru Camp - 05 (AT-X 1280x720 x264 AAC).mp4",
     *       "episode": 5,
     *       "tokenthumb": "bB-8KQuoc6u-1SfzuVnDMw",
     *       "similarity": 0.9563952960290518,
     *       "title": "ゆるキャン△",
     *       "title_native": "ゆるキャン△",
     *       "title_chinese": "搖曳露營",
     *       "title_english": "Laid-Back Camp",
     *       "title_romaji": "Yuru Camp△",
     *       "mal_id": 34798,
     *       "synonyms": ["Yurucamp", "Yurukyan△"],
     *       "synonyms_chinese": [],
     *       "is_adult": false
     *     }
     *   ]
     */
    @Data
    public static class Doc {
        private float from;
        private float to;
        private String filename;
        private String episode;
        private String video;
        private String image;
        private double similarity;
        private int anilist;
    }
}
