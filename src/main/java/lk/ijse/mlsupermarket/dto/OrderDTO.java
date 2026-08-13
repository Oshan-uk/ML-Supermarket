package lk.ijse.mlsupermarket.dto;

import lk.ijse.mlsupermarket.status.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    private LocalDate orderDate;
    private Double discount;
    private Double total;
    private OrderStatus orderStatus;
    private Long customerId;
    private Long userId;
    private List<OrderItemDTO> itemList;

}
