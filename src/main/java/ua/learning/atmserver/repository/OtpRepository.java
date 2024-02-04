package ua.learning.atmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.learning.atmserver.entity.Otp;

public interface OtpRepository extends JpaRepository<Otp, Integer> {
    Otp findOtpByClientClientIdAndAtmAtmId(int clientId, int atmId);
}
