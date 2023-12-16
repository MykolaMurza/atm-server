package ua.learning.atmserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@Entity
@Setter
@Getter
public class Atm {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "atm_id", nullable = false)
    private int atmId;

    @Basic
    @Column(name = "location", nullable = false, length = 128)
    private String location;

    @Basic
    @Column(name = "manufacturer", nullable = false, length = 45)
    private String manufacturer;

    @OneToMany(mappedBy = "atm")
    private Collection<AtmAuditLog> logs;

    @OneToMany(mappedBy = "atm")
    private Collection<Transaction> transactions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Atm atm = (Atm) o;

        if (atmId != atm.atmId) return false;
        if (!Objects.equals(location, atm.location)) return false;
        return Objects.equals(manufacturer, atm.manufacturer);
    }

    @Override
    public int hashCode() {
        int result = atmId;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        return result;
    }
}
