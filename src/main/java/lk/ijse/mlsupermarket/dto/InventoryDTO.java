package lk.ijse.mlsupermarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {
    private Long inventoryId;
    private Long productId;
    private Integer quantity;
    private Integer reorderLevel;

}
