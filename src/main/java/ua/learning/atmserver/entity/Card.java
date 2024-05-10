package ua.learning.atmserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.learning.atmserver.entity.enums.Status;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cards")
public class Card {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "number", nullable = false, length = 16)
    private String number;

    @Basic
    @Column(name = "cardholder", nullable = false, length = 40)
    private String cardholder;

    @Basic
    @Column(name = "type", nullable = false, length = 30)
    private String type;

    @Basic
    @Column(name = "format", nullable = false, length = 30)
    private String format;

    @Basic
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Basic
    @Column(name = "expires_at", nullable = false)
    private LocalDate expiresAt;

    @Basic
    @Column(name = "status", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Basic
    @Column(name = "CVV", nullable = false, length = 3)
    private String CVV;

    @Basic
    @Column(name = "PIN", nullable = false, length = 4)
    private String PIN;

    @JoinColumn(name = "account_id", nullable = false)
    @ManyToOne
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;
        return id == card.id
                && Objects.equals(number, card.number)
                && Objects.equals(cardholder, card.cardholder)
                && Objects.equals(type, card.type)
                && Objects.equals(format, card.format)
                && Objects.equals(createdAt, card.createdAt)
                && Objects.equals(expiresAt, card.expiresAt)
                && Objects.equals(status, card.status)
                && Objects.equals(CVV, card.CVV)
                && Objects.equals(PIN, card.PIN);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Objects.hashCode(number);
        result = 31 * result + Objects.hashCode(cardholder);
        result = 31 * result + Objects.hashCode(type);
        result = 31 * result + Objects.hashCode(format);
        result = 31 * result + Objects.hashCode(createdAt);
        result = 31 * result + Objects.hashCode(expiresAt);
        result = 31 * result + Objects.hashCode(status);
        result = 31 * result + Objects.hashCode(CVV);
        result = 31 * result + Objects.hashCode(PIN);
        return result;
    }
}
