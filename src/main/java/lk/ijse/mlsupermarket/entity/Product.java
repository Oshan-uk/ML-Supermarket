package lk.ijse.mlsupermarket.entity;

import jakarta.persistence.*;
import lk.ijse.mlsupermarket.status.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private Double unitPrice;
    private String barcode;

    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.ACTIVE;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
