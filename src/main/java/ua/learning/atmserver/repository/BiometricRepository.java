package ua.learning.atmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.learning.atmserver.entity.Biometric;

@Repository
public interface BiometricRepository extends JpaRepository<Biometric, Integer> {
    Biometric findBiometricByClientId(int clientId);
}
