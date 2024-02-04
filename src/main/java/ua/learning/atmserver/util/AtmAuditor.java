package ua.learning.atmserver.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.learning.atmserver.entity.Atm;
import ua.learning.atmserver.entity.AtmAuditLog;
import ua.learning.atmserver.entity.Client;
import ua.learning.atmserver.repository.AtmAuditLogRepository;

import java.sql.Timestamp;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class AtmAuditor {
    private final AtmAuditLogRepository auditLogRepository;

    public void log(String action, Client client, Atm atm) {
        AtmAuditLog log = new AtmAuditLog();
        log.setAction(action);
        log.setTimestamp(Timestamp.from(Instant.now()));
        log.setClient(client);
        log.setAtm(atm);
        auditLogRepository.save(log);
    }
}
