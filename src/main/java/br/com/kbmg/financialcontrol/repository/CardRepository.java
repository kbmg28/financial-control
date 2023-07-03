package br.com.kbmg.financialcontrol.repository;

import br.com.kbmg.financialcontrol.model.Account;
import br.com.kbmg.financialcontrol.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

    List<Card> findByAccount(Account account);

}
