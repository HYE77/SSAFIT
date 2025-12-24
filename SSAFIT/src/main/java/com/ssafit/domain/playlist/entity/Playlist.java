package com.ssafit.domain.playlist.entity;

import org.apache.ibatis.type.Alias;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Alias("Playlist")
public class Playlist {

    private Long id;

    private Long publisherId;   // 생성자 (user.id)

    private String title;
    private String description;
}
