package lk.ijse.mlsupermarket.dto;

import lk.ijse.mlsupermarket.status.MovementType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockMovementDTO {
    private Long movementId;
    private Long productId;
    private MovementType movementType;
    private Integer quantity;
    private LocalDate movementDate;
    private String reason;
}
