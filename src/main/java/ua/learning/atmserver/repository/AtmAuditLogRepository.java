package ua.learning.atmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.learning.atmserver.entity.AtmAuditLog;

@Repository
public interface AtmAuditLogRepository extends JpaRepository<AtmAuditLog, Integer> {
}
