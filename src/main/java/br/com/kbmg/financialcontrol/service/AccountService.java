package br.com.kbmg.financialcontrol.service;

import br.com.kbmg.financialcontrol.model.Account;
import br.com.kbmg.financialcontrol.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public record AccountService(AccountRepository accountRepository) {

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccountByEmail(String email) {
        return accountRepository.findByEmail(email).orElse(null);
    }

}