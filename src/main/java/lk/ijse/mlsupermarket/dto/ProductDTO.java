package lk.ijse.mlsupermarket.dto;

import lk.ijse.mlsupermarket.status.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long productId;
    private String productName;
    private Double unitPrice;
    private String barcode;
    private ProductStatus status;
    private Long categoryId;
}
