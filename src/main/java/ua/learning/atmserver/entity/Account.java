package ua.learning.atmserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ua.learning.atmserver.entity.enums.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "accounts")
public class Account {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "number", nullable = false, length = 110)
    private String number;

    @Basic
    @Column(name = "bank", nullable = false, length = 45)
    private String bank;

    @Basic
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Basic
    @Column(name = "actual_balance", nullable = false, length = 15)
    private String actual_balance;

    @Basic
    @Column(name = "type", nullable = false, length = 30)
    private String type;

    @Basic
    @Column(name = "status", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private Status status;

    @JoinColumn(name = "client_id", nullable = false)
    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "account")
    private List<Card> cards;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;
        return id == account.id
                && Objects.equals(number, account.number)
                && Objects.equals(bank, account.bank)
                && Objects.equals(createdAt, account.createdAt)
                && Objects.equals(actual_balance, account.actual_balance)
                && Objects.equals(type, account.type)
                && Objects.equals(status, account.status);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Objects.hashCode(number);
        result = 31 * result + Objects.hashCode(bank);
        result = 31 * result + Objects.hashCode(createdAt);
        result = 31 * result + Objects.hashCode(actual_balance);
        result = 31 * result + Objects.hashCode(type);
        result = 31 * result + Objects.hashCode(status);
        return result;
    }
}
