package lk.ijse.mlsupermarket.entity;

import jakarta.persistence.*;
import lk.ijse.mlsupermarket.status.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private String message;
    private String recipient;
    private String status;
    private LocalDate sentDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
