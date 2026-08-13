package lk.ijse.mlsupermarket.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDTO {
    private Long totalCustomers;
    private Long totalOrders;
    private Double totalRevenue;
    private Long lowStockCount;
}
