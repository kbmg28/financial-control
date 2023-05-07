package br.com.kbmg.financialcontrol.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountDto {

    private Long id;
    private String name;

    @NotBlank
    @Email
    private String email;

}
