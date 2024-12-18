package com.bdool.chatservice.sse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelDeleteResponse {
    private UUID channelId;
    private Long workspacesId;
}
