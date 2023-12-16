package ua.learning.atmserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Entity
@Getter
@Setter
public class Biometric {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "biometric_id", nullable = false)
    private int biometricId;

    @Basic
    @Column(name = "biometric_data", nullable = false)
    private byte[] biometricData;

    @JoinColumn(name = "client_client_id", nullable = false)
    @OneToOne
    private Client client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Biometric biometric = (Biometric) o;

        if (biometricId != biometric.biometricId) return false;
        if (!client.equals(biometric.client)) return false;
        return Arrays.equals(biometricData, biometric.biometricData);
    }

    @Override
    public int hashCode() {
        int result = biometricId;
        result = 31 * result + Arrays.hashCode(biometricData);
        result = 31 * result + client.hashCode();
        return result;
    }
}
