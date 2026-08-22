package lk.ijse.mlsupermarket.repository;

import lk.ijse.mlsupermarket.entity.ChatbotLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatbotLogRepository extends JpaRepository<ChatbotLog, Long> {
    List<ChatbotLog> findByUser_UserId(Long userId);
}
