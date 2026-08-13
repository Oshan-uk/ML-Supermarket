package lk.ijse.mlsupermarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDTO {
    private Long discountId;
    private String discountName;
    private Double discountPercentage;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long productId;
}
