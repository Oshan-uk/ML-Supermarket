package lk.ijse.mlsupermarket.controller;

import lk.ijse.mlsupermarket.constant.CommonResponse;
import lk.ijse.mlsupermarket.constant.ResponseCode;
import lk.ijse.mlsupermarket.constant.ResponseMessage;
import lk.ijse.mlsupermarket.dto.ChatRequestDTO;
import lk.ijse.mlsupermarket.dto.ChatResponseDTO;
import lk.ijse.mlsupermarket.service.ChatbotService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chatbot")
public class ChatbotController {
    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping(value = "/ask", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse askChatbot(@RequestBody ChatRequestDTO chatRequestDTO) {
        ChatResponseDTO response = chatbotService.askChatbot(chatRequestDTO);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, response, ResponseMessage.SUCCESS_MESSAGE);
    }
}