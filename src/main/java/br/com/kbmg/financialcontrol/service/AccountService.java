package br.com.kbmg.financialcontrol.service;

import br.com.kbmg.financialcontrol.exceptions.FinancialControlException;
import br.com.kbmg.financialcontrol.model.Account;
import br.com.kbmg.financialcontrol.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kbmg.financialcontrol.util.KeyMessageConstants.USER_NOT_FOUND;

@Service
public record AccountService(AccountRepository accountRepository) {

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }
    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow();
    }

    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public Account findByEmailValidated(String email) {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new FinancialControlException(USER_NOT_FOUND, HttpStatus.CONFLICT));
    }

}