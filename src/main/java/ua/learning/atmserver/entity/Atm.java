package ua.learning.atmserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "atms")
public class Atm {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "location", nullable = false)
    private String location;

    @Basic
    @Column(name = "manufacturer", nullable = false, length = 45)
    private String manufacturer;

    @Basic
    @Column(name = "version", nullable = false, length = 12)
    private String version;

    @OneToMany(mappedBy = "atm")
    private Collection<AtmAuditLog> logs;

    @OneToMany(mappedBy = "atm")
    private Collection<Transaction> transactions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Atm atm = (Atm) o;

        if (id != atm.id) return false;
        if (!Objects.equals(location, atm.location)) return false;
        return Objects.equals(manufacturer, atm.manufacturer);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        return result;
    }
}
