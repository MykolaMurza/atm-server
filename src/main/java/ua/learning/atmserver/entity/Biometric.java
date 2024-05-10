package ua.learning.atmserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Entity
@Getter
@Setter
@Table(name = "biometrics")
public class Biometric {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "fingerprint", nullable = false)
    private byte[] fingerprint;

    @JoinColumn(name = "client_id", nullable = false)
    @OneToOne
    private Client client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Biometric biometric = (Biometric) o;

        if (id != biometric.id) return false;
        return Arrays.equals(fingerprint, biometric.fingerprint);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Arrays.hashCode(fingerprint);
        return result;
    }
}
