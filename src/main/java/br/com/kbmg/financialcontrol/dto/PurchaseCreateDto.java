package br.com.kbmg.financialcontrol.dto;

import br.com.kbmg.financialcontrol.enumerators.PurchaseType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseCreateDto {

    private Long id;

    @NotNull
    private PurchaseType type;

    @NotNull
    private BigDecimal totalValue;

    @NotNull
    private OffsetDateTime dateOfPurchase;

    private Integer installments;

    @NotBlank
    private String productName;

    private LocalDate expirationDate;

    @NotNull
    private BigDecimal price;
    private CardDto card;

}
