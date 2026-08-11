package lk.ijse.mlsupermarket.dto;

import lk.ijse.mlsupermarket.status.UserRoleStatus;
import lk.ijse.mlsupermarket.status.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long userId;
    private String userName;
    private String password;
    private String contact;
    private UserRoleStatus userRole;
    private UserStatus status;
}
