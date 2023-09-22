package br.com.kbmg.financialcontrol.controller;

import br.com.kbmg.financialcontrol.dto.JwtTokenDto;
import br.com.kbmg.financialcontrol.dto.LoginDto;
import br.com.kbmg.financialcontrol.dto.SignUpDto;
import br.com.kbmg.financialcontrol.service.SecurityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class SecurityController {

    private final SecurityService securityService;


    @PostMapping("/log-in")
    @Transactional
    public JwtTokenDto loginAndGetToken(
            @RequestBody @Valid LoginDto loginDto) {
        String token = securityService.login(loginDto);

        return new JwtTokenDto(token);
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUserAccount(
            @RequestBody @Valid SignUpDto signUpDto) {
        securityService.signUp(signUpDto);
    }

}
