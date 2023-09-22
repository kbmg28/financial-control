package br.com.kbmg.financialcontrol.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtTokenDto {
    private String token;
}
