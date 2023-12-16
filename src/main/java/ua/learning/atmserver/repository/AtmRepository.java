package ua.learning.atmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.learning.atmserver.entity.Atm;

@Repository
public interface AtmRepository extends JpaRepository<Atm, Integer> {
}
