package ua.learning.atmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.learning.atmserver.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
