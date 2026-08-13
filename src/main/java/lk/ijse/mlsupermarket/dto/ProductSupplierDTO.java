package lk.ijse.mlsupermarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSupplierDTO {
    private Long productSupplierId;
    private Long productId;
    private Long supplierId;
    private Double costPrice;

}
