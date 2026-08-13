package lk.ijse.mlsupermarket.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockLevelDTO {
    private Long productId;
    private String productName;
    private Integer quantity;
}
