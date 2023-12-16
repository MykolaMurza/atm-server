package ua.learning.atmserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "client_id", nullable = false)
    private int clientId;

    @Basic
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Basic
    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Basic
    @Column(name = "phone", nullable = false, length = 14)
    private String phone;

    @Basic
    @Column(name = "account_number", nullable = false, length = 52)
    private String accountNumber;

    @OneToOne(mappedBy = "client")
    private Biometric biometric;

    @OneToMany(mappedBy = "client")
    private Collection<AtmAuditLog> logs;

    @OneToMany(mappedBy = "client")
    private Collection<Transaction> transactions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (clientId != client.clientId) return false;
        if (!Objects.equals(firstName, client.firstName)) return false;
        if (!Objects.equals(lastName, client.lastName)) return false;
        if (!Objects.equals(email, client.email)) return false;
        if (!Objects.equals(phone, client.phone)) return false;
        return Objects.equals(accountNumber, client.accountNumber);
    }

    @Override
    public int hashCode() {
        int result = clientId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (accountNumber != null ? accountNumber.hashCode() : 0);
        return result;
    }
}
