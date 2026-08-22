package lk.ijse.mlsupermarket.service;

import lk.ijse.mlsupermarket.dto.ChatRequestDTO;
import lk.ijse.mlsupermarket.dto.ChatResponseDTO;

public interface ChatbotService {
    public ChatResponseDTO askChatbot(ChatRequestDTO chatRequestDTO);
}
