package lk.ijse.mlsupermarket.entity;

import jakarta.persistence.*;
import lk.ijse.mlsupermarket.status.PaymentMethod;
import lk.ijse.mlsupermarket.status.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private LocalDate paymentDate;

    @OneToOne
    @JoinColumn(name = "order_id", unique = true)
    private Orders order;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
