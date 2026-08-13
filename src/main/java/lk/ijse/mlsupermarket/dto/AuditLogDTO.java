package lk.ijse.mlsupermarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditLogDTO {
    private Long auditId;
    private Long userId;
    private String action;
    private String entityAffected;
    private LocalDate timestamp;
}
