package com.bdool.chatservice.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Document(collection = "participants")
public class ParticipantEntity {

    @Id
    private String id; // MongoDB 기본 `_id` 필드

    @Field(name = "participant_id")
    private UUID participantId;

    @Field(name = "channel_id")
    private UUID channelId;

    private String nickname;

    private LocalDateTime joinedAt; // LocalDateTime.now()로 처리

    private Boolean isOnline;

    @Field(name = "profile_id")
    private Long profileId;

    private String profileUrl;

    public void updateOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateProfileURL(String profileUrl) {
        this.profileUrl = this.profileUrl;
    }
}
