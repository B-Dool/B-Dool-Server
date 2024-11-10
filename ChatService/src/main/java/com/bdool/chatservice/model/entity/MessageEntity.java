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
    @Field(name = "message_id")
    private String messageId;

    @Field(name = "channel_id")
    private String channelId;

    private String content;

    @Field(name = "send_date")
    private String sendDate;

    @Field(name = "is_deleted")
    private Long isDeleted;

    @Field(name = "is_edited")
    private Long isEdited;

    @Field(name = "parent_message_id")
    private  String parentMessageId;

    @Field(name = "profile_id")
    private Long profileId;

    @Field(name = "file_URL")
    private String fileURL;
}