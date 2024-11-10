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
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "messages")
public class MessageEntity {
    @Id
    private String id; // MongoDB 기본 `_id` 필드

    @Field(name = "message_id")
    private UUID messageId;

    @Field(name = "channel_id")
    private UUID channelId;

    private String content;

    @Field(name = "send_date")
    private LocalDateTime sendDate;

    @Field(name = "is_deleted")
    private Boolean isDeleted;

    @Field(name = "is_edited")
    private Boolean isEdited;

    @Field(name = "parent_message_id")
    private UUID parentMessageId;

    @Field(name = "profile_id")
    private Long profileId;

    @Field(name = "file_URL")
    private String fileUrl;
}