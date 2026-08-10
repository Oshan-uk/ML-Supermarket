package lk.ijse.mlsupermarket.entity;

import jakarta.persistence.*;
import lk.ijse.mlsupermarket.status.UserRoleStatus;
import lk.ijse.mlsupermarket.status.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String password;
    private String contact;

    @Enumerated(EnumType.STRING)
    private UserRoleStatus userRole;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

}
