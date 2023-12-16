package ua.learning.atmserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Transaction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "transaction_id", nullable = false)
    private int transactionId;

    @Basic
    @Column(name = "amount", nullable = false)
    private int amount;

    @Basic
    @Column(name = "type", nullable = false, length = 45)
    private String type;

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

        Transaction that = (Transaction) o;

        if (transactionId != that.transactionId) return false;
        if (amount != that.amount) return false;
        if (!client.equals(that.client)) return false;
        if (!atm.equals(that.atm)) return false;
        if (!Objects.equals(type, that.type)) return false;
        return Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        int result = transactionId;
        result = 31 * result + amount;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + client.hashCode();
        result = 31 * result + atm.hashCode();
        return result;
    }
}
