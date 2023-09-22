package br.com.kbmg.financialcontrol.service;

import br.com.kbmg.financialcontrol.dto.LoginDto;
import br.com.kbmg.financialcontrol.dto.SignUpDto;
import br.com.kbmg.financialcontrol.exceptions.BadUserInfoException;
import br.com.kbmg.financialcontrol.mapper.AccountMapper;
import br.com.kbmg.financialcontrol.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import static br.com.kbmg.financialcontrol.util.KeyMessageConstants.USER_OR_PASSWORD_INCORRECT;

@Service
@Slf4j
public record SecurityService(AccountService accountService,
                              JwtService jwtService) {

    public String login(LoginDto loginDto) {
        String email = loginDto.getEmail().toLowerCase();
        Account account = accountService
                .findByEmail(email)
                .orElseThrow(() -> new BadUserInfoException(USER_OR_PASSWORD_INCORRECT));

        validatePassword(loginDto.getPassword(), account.getPassword());

        return jwtService.generateToken(loginDto);
    }

    public void signUp(SignUpDto signUpDto) {
        Account account = AccountMapper.INSTANCE.toNewAccount(signUpDto);
        account = accountService.saveAccount(account);
        log.info("Successful created new account {}", account);
    }

    private void validatePassword(String plainTextPassword, String hashPassword) {
        boolean isCorrectPassword = BCrypt.checkpw(plainTextPassword, hashPassword);

        if (!isCorrectPassword) {
            throw new BadUserInfoException(USER_OR_PASSWORD_INCORRECT);
        }
    }

}
