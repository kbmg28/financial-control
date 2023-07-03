package br.com.kbmg.financialcontrol.dto;

import br.com.kbmg.financialcontrol.enumerators.PurchaseType;
import br.com.kbmg.financialcontrol.model.Expense;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDto {

    private Long id;
    private PurchaseType type;
    private BigDecimal totalValue;
    private OffsetDateTime dateOfPurchase;
    private Integer installments;
    private String productName;
    private Set<Expense> expenseList;

}
