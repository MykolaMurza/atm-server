package ua.learning.atmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.learning.atmserver.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    Card findCardByNumber(String cardNumber);
}
