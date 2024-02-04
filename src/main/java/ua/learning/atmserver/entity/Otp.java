package ua.learning.atmserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Otp {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "otp_id", nullable = false)
    private int otpId;

    @Basic
    @Column(name = "otp_code", nullable = false, length = 6)
    private String otpCode;

    @Basic
    @Column(name = "time_to_live", nullable = false)
    private int timeToLive;

    @Basic
    @Column(name = "created_at", nullable = false)
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

        Otp otp = (Otp) o;

        if (otpId != otp.otpId) return false;
        if (!Objects.equals(client, otp.client)) return false;
        return Objects.equals(atm, otp.atm);
    }

    @Override
    public int hashCode() {
        int result = otpId;
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (atm != null ? atm.hashCode() : 0);
        return result;
    }
}
