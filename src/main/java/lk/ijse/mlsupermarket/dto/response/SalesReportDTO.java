package lk.ijse.mlsupermarket.dto.response;

import lk.ijse.mlsupermarket.status.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesReportDTO {
    private LocalDate orderDate;
    private Long orderId;
    private String customerName;
    private OrderStatus orderStatus;
    private Double total;
}
