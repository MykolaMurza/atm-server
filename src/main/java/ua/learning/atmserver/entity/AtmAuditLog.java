package ua.learning.atmserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "audit_logs")
public class AtmAuditLog {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "action", nullable = false, length = 45)
    private String action;

    @Basic
    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @JoinColumn(name = "client_id", nullable = false)
    @ManyToOne
    private Client client;

    @JoinColumn(name = "atm_id", nullable = false)
    @ManyToOne
    private Atm atm;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AtmAuditLog that = (AtmAuditLog) o;

        if (id != that.id) return false;
        if (!Objects.equals(action, that.action)) return false;
        return Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }
}


