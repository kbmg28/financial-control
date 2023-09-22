package br.com.kbmg.financialcontrol.controller;

import br.com.kbmg.financialcontrol.dto.AccountDto;
import br.com.kbmg.financialcontrol.mapper.AccountMapper;
import br.com.kbmg.financialcontrol.model.Account;
import br.com.kbmg.financialcontrol.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{email}")
    public ResponseEntity<AccountDto> getAccountByEmail(@PathVariable String email) {
        Account account = accountService.findByEmailValidated(email);
        AccountDto accountDto = AccountMapper.INSTANCE.toDto(account);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

}
