package br.com.kbmg.financialcontrol.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDto {

    private Long id;

    @NotNull
    private BigDecimal salaryValue;

    @NotNull
    private LocalDate receivedDate;

    @NotNull
    @Schema(example = "2023-05")
    private YearMonth yearMonth;

    @Valid
    @NotNull
    private SalarySourceDto salarySource;

}
