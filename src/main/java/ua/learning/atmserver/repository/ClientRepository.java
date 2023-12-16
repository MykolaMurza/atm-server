package ua.learning.atmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.learning.atmserver.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findClientByAccountNumber(String account);
}
