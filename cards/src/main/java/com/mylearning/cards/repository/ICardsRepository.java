package com.mylearning.cards.repository;

import com.mylearning.cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICardsRepository extends JpaRepository<Cards,Long> {

    public Optional<Cards>  findByMobileNumber(String mobileNumber);

    public Optional<Cards> findByCardNumber(String cardNumber);

}
