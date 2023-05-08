package br.com.kbmg.financialcontrol.controller;

import br.com.kbmg.financialcontrol.service.AccountService;
import br.com.kbmg.financialcontrol.dto.AccountDto;
import br.com.kbmg.financialcontrol.mapper.AccountMapper;
import br.com.kbmg.financialcontrol.model.Account;
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

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{email}")
    public ResponseEntity<AccountDto> getAccountByEmail(@PathVariable String email) {
        Account account = accountService.getAccountByEmail(email);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AccountDto accountDto = AccountMapper.INSTANCE.toDto(account);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody AccountDto accountDto) {
        Account account = AccountMapper.INSTANCE.toEntity(accountDto);
        account = accountService.saveAccount(account);
        AccountDto savedAccountDto = AccountMapper.INSTANCE.toDto(account);
        return new ResponseEntity<>(savedAccountDto, HttpStatus.CREATED);
    }
}
