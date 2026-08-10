package lk.ijse.mlsupermarket.repository;

import lk.ijse.mlsupermarket.entity.ChatbotLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatbotLogRepository extends JpaRepository<ChatbotLog, Long> {
}
