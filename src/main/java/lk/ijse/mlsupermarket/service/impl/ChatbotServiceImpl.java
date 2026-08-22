package lk.ijse.mlsupermarket.service.impl;

import lk.ijse.mlsupermarket.dto.ChatRequestDTO;
import lk.ijse.mlsupermarket.dto.ChatResponseDTO;
import lk.ijse.mlsupermarket.entity.ChatbotLog;
import lk.ijse.mlsupermarket.entity.User;
import lk.ijse.mlsupermarket.repository.*;
import lk.ijse.mlsupermarket.service.ChatbotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class ChatbotServiceImpl implements ChatbotService {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final InventoryRepository inventoryRepository;
    private final ChatbotLogRepository chatbotLogRepository;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @Value("${ai.api.key}")
    private String apiKey;

    @Value("${ai.api.url}")
    private String apiUrl;

    @Value("${ai.api.model}")
    private String model;

    public ChatbotServiceImpl(CustomerRepository customerRepository, ProductRepository productRepository, OrderRepository orderRepository, InventoryRepository inventoryRepository, ChatbotLogRepository chatbotLogRepository, UserRepository userRepository, RestTemplate restTemplate) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.inventoryRepository = inventoryRepository;
        this.chatbotLogRepository = chatbotLogRepository;
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public ChatResponseDTO askChatbot(ChatRequestDTO chatRequestDTO) {
        log.info("Execute askChatbot() query", chatRequestDTO.getQuery());
        try {
            String query = chatRequestDTO.getQuery().toLowerCase();
            Object contextData = detectIntentAndFetchData(query);

            String aiAnswer = callAiApi(chatRequestDTO.getQuery(), contextData);

            // save to ChatbotLog
            Optional<User> optionalUser = userRepository.findById(chatRequestDTO.getUserId());
            if (optionalUser.isPresent()) {
                ChatbotLog chatbotLog = new ChatbotLog();
                chatbotLog.setUser(optionalUser.get());
                chatbotLog.setQuery(chatRequestDTO.getQuery());
                chatbotLog.setResponse(aiAnswer);
                chatbotLog.setCreatedAt(LocalDate.now());
                chatbotLogRepository.save(chatbotLog);
            }

            return new ChatResponseDTO(aiAnswer);

        } catch (Exception e) {
            log.error("Error in askChatbot()" + e.getMessage());
            throw e;
        }
    }

    private Object detectIntentAndFetchData(String query) {
        Map<String, Object> context = new HashMap<>();

        if (query.contains("customer")) {
            context.put("totalCustomers", customerRepository.count());

        } else if (query.contains("revenue") || query.contains("sales")) {
            Double totalRevenue = orderRepository.findAll().stream()
                    .mapToDouble(o -> o.getTotal() != null ? o.getTotal() : 0.0)
                    .sum();
            context.put("totalRevenue", totalRevenue);
            context.put("totalOrders", orderRepository.count());

        } else if (query.contains("low stock") || query.contains("low-stock") || query.contains("reorder")) {
            List<String> lowStockProducts = inventoryRepository.findAll().stream()
                    .filter(i -> i.getQuantity() <= i.getReorderLevel())
                    .map(i -> i.getProduct().getProductName() + " (qty: " + i.getQuantity() + ")")
                    .toList();
            context.put("lowStockProducts", lowStockProducts);

        } else if (query.contains("price") || query.contains("cost")) {
            context.put("products", productRepository.findAll().stream()
                    .map(p -> p.getProductName() + ": Rs." + p.getUnitPrice())
                    .toList());

        } else if (query.contains("stock") || query.contains("quantity") || query.contains("inventory")) {
            context.put("inventory", inventoryRepository.findAll().stream()
                    .map(i -> i.getProduct().getProductName() + ": " + i.getQuantity() + " units")
                    .toList());

        } else {
            context.put("note", "No specific store data matched this query — answer generally if possible.");
        }

        return context;
    }

    private String callAiApi(String userQuery, Object contextData) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            String systemPrompt = "You are a helpful assistant for ML Supermarket's management system. "
                    + "Answer the user's question using ONLY the following real data: " + contextData.toString()
                    + ". Keep the answer short, clear, and in plain English.";

            Map<String, Object> body = new HashMap<>();
            body.put("model", model);
            body.put("messages", List.of(
                    Map.of("role", "system", "content", systemPrompt),
                    Map.of("role", "user", "content", userQuery)
            ));

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

            Map response = restTemplate.postForObject(apiUrl, request, Map.class);

            List<Map> choices = (List<Map>) response.get("choices");
            Map message = (Map) choices.get(0).get("message");
            return (String) message.get("content");

        } catch (Exception e) {
            log.error("Error calling AI API : " + e.getMessage());
            return "Sorry, I couldn't process that right now. Here's the raw data: " + contextData.toString();
        }
    }
}