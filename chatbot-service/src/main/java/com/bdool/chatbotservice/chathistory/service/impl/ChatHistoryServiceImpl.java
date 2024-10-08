package com.bdool.chatbotservice.chathistory.service.impl;

import com.bdool.chatbotservice.ai.service.OpenAiChatService;
import com.bdool.chatbotservice.botresponse.entity.BotResponse;
import com.bdool.chatbotservice.botresponse.service.BotResponseService;
import com.bdool.chatbotservice.chathistory.entity.ChatHistory;
import com.bdool.chatbotservice.chathistory.repository.ChatHistoryRepository;
import com.bdool.chatbotservice.chathistory.service.ChatHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChatHistoryServiceImpl implements ChatHistoryService {

    private final ChatHistoryRepository chatHistoryRepository;
    private final BotResponseService botResponseService;
    private final OpenAiChatService openAiChatService;
    private final String modelUsed = "gpt-3.5";

    @Override
    public String processChat(Long workspaceId, Long profileId, Map<String, String> requestBody) {
        return processChatInternal(workspaceId, profileId, requestBody, false);
    }

    @Override
    public String processTranslate(Long workspaceId, Long profileId, Map<String, String> requestBody) {
        return processChatInternal(workspaceId, profileId, requestBody, true);
    }

    private String processChatInternal(Long workspaceId, Long profileId, Map<String, String> requestBody, boolean isTranslation) {
        String question = requestBody.get("question");

        return botResponseService.findCachedResponse(question)
                .map(cachedResponse -> {
                    saveChatHistory(workspaceId, profileId, question, cachedResponse);
                    return cachedResponse.getResponseText();
                })
                .orElseGet(() -> {
                    String aiResponse = isTranslation ? openAiChatService.getChatGPTTranslate(question)
                            : openAiChatService.getChatGPTResponse(question);
                    BotResponse botResponse = botResponseService.saveResponse(aiResponse, modelUsed);
                    saveChatHistory(workspaceId, profileId, question, botResponse);
                    return botResponse.getResponseText();
                });
    }

    @Override
    public List<ChatHistory> getChatHistory(Long workspaceId, Long profileId) {
        return chatHistoryRepository.findByWorkspaceIdAndProfileId(workspaceId, profileId);
    }

    private void saveChatHistory(Long workspaceId, Long profileId, String question, BotResponse cachedResponse) {
        ChatHistory chatHistory = ChatHistory.builder()
                .workspaceId(workspaceId)
                .profileId(profileId)
                .question(question)
                .botResponse(cachedResponse)
                .build();
        chatHistoryRepository.save(chatHistory);
    }
}