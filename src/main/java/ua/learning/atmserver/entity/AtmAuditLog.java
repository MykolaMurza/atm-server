package ua.learning.atmserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "atm_audit_log", schema = "atm_db")
@Setter
@Getter
public class AtmAuditLog {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "log_id", nullable = false)
    private int logId;

    @Basic
    @Column(name = "action", nullable = false, length = 45)
    private String action;

    @Basic
    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

    @JoinColumn(name = "client_client_id", nullable = false)
    @ManyToOne
    private Client client;

    @JoinColumn(name = "atm_atm_id", nullable = false)
    @ManyToOne
    private Atm atm;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AtmAuditLog that = (AtmAuditLog) o;

        if (logId != that.logId) return false;
        if (!client.equals(that.client)) return false;
        if (!atm.equals(that.atm)) return false;
        if (!Objects.equals(action, that.action)) return false;
        return Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        int result = logId;
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + client.hashCode();
        result = 31 * result + atm.hashCode();
        return result;
    }
}
