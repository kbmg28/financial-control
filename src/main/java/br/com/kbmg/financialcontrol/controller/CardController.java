package br.com.kbmg.financialcontrol.controller;

import br.com.kbmg.financialcontrol.dto.CardDto;
import br.com.kbmg.financialcontrol.mapper.CardMapper;
import br.com.kbmg.financialcontrol.service.AccountService;
import br.com.kbmg.financialcontrol.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final AccountService accountService;
    private final CardService cardService;

    @GetMapping("/{email}")
    public ResponseEntity<List<CardDto>> findByAccount(@PathVariable String email) {
        var account = accountService.getAccountByEmail(email);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var cards = cardService.findByAccount(account);
        var cardDtoList = CardMapper.INSTANCE.toCardDtoList(cards);
        return new ResponseEntity<>(cardDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CardDto> createCard(@Valid @RequestBody CardDto cardDto) {
        var card = CardMapper.INSTANCE.toCard(cardDto);
        card = cardService.createCard(card);
        var saved = CardMapper.INSTANCE.toCardDto(card);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
