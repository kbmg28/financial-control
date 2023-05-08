package br.com.kbmg.financialcontrol.controller;

import br.com.kbmg.financialcontrol.dto.SalaryDto;
import br.com.kbmg.financialcontrol.dto.SalarySourceDto;
import br.com.kbmg.financialcontrol.mapper.SalaryMapper;
import br.com.kbmg.financialcontrol.service.SalaryService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/api/salaries")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryService salaryService;

    @GetMapping
    public ResponseEntity<List<SalaryDto>> getSalariesByYearMonth(@RequestParam Long accountId,
                                                            @Parameter(example = "2023-05")  @RequestParam YearMonth yearMonth) {
        var salaries = salaryService.findByAccountIdAndYearMonth(accountId, yearMonth);
        var salaryDtoList = SalaryMapper.INSTANCE.toSalaryDtoList(salaries);
        return new ResponseEntity<>(salaryDtoList, HttpStatus.OK);
    }

    @PostMapping("/sources")
    public ResponseEntity<SalarySourceDto> createSalarySource(@Valid @RequestBody SalarySourceDto salarySourceDto) {
        var salarySource = SalaryMapper.INSTANCE.toSalarySource(salarySourceDto);
        salarySource = salaryService.createSalarySource(salarySource);
        var savedAccountDto = SalaryMapper.INSTANCE.toSalarySourceDto(salarySource);
        return new ResponseEntity<>(savedAccountDto, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<SalaryDto> addSalary(@RequestParam Long accountId,
                                             @Valid @RequestBody SalaryDto salaryDto) {
        var salary = SalaryMapper.INSTANCE.toSalary(salaryDto);
        salary = salaryService.addSalary(salary, salaryDto.getSalarySource(), accountId);
        var savedSalaryDto = SalaryMapper.INSTANCE.toSalaryDto(salary);
        return new ResponseEntity<>(savedSalaryDto, HttpStatus.CREATED);
    }
}
