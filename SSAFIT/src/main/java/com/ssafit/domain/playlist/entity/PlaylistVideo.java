package com.ssafit.domain.playlist.entity;

import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Alias("PlaylistVideo")
public class PlaylistVideo {

    private Long id;

    private Long playlistId;
    private Long videoId;

    private LocalDateTime regDate;
    private Boolean isDeleted;
}
