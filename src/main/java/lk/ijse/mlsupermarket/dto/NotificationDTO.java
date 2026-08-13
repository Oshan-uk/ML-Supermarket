package lk.ijse.mlsupermarket.dto;

import lk.ijse.mlsupermarket.status.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {
    private Long notificationId;
    private NotificationType type;
    private String message;
    private String recipient;
    private String status;
    private Long productId;
    private LocalDate sentDate;
}
