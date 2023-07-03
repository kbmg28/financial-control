package br.com.kbmg.financialcontrol.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDto {

    private Long id;
    private BigDecimal price;
    private Integer installment;
    private OffsetDateTime expirationDate;
    private OffsetDateTime paymentDate;

}