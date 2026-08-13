package lk.ijse.mlsupermarket.dto;

import lk.ijse.mlsupermarket.status.PaymentMethod;
import lk.ijse.mlsupermarket.status.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private Long paymentId;
    private Long orderId;
    private Double amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private LocalDate paymentDate;
}
