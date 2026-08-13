package lk.ijse.mlsupermarket.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockReportDTO {
    private Long productId;
    private String productName;
    private Integer quantity;
    private Integer reorderLevel;
}
