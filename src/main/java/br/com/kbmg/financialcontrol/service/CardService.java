package br.com.kbmg.financialcontrol.service;

import br.com.kbmg.financialcontrol.model.Account;
import br.com.kbmg.financialcontrol.model.Card;
import br.com.kbmg.financialcontrol.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record CardService(CardRepository cardRepository) {

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public List<Card> findByAccount(Account account) {
        return cardRepository.findByAccount(account);
    }

}