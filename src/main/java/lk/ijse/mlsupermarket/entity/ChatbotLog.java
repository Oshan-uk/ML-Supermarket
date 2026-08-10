package lk.ijse.mlsupermarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChatbotLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatbotLogId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String query;
    private String response;
    private LocalDate createdAt;
}
