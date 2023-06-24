package br.com.kbmg.financialcontrol.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private Long id;

    @NotNull
    private String finalNumber;

    @NotNull
    private LocalDate paymentDate;

}
