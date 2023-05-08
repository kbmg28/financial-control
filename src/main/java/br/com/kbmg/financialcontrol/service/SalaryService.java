package br.com.kbmg.financialcontrol.service;

import br.com.kbmg.financialcontrol.dto.SalarySourceDto;
import br.com.kbmg.financialcontrol.model.Account;
import br.com.kbmg.financialcontrol.model.Salary;
import br.com.kbmg.financialcontrol.model.SalarySource;
import br.com.kbmg.financialcontrol.repository.SalaryRepository;
import br.com.kbmg.financialcontrol.repository.SalarySourceRepository;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;

@Service
public record SalaryService(SalaryRepository salaryRepository,
                            SalarySourceRepository salarySourceRepository,
                            AccountService accountService) {
    public SalarySource createSalarySource(SalarySource salarySource) {
        return salarySourceRepository.save(salarySource);
    }

    public Salary addSalary(Salary salary, SalarySourceDto salarySourceDto, Long accountId) {
        SalarySource salarySource = salarySourceRepository.findById(salarySourceDto.getId()).orElseThrow();
        Account account = accountService.findById(accountId);
        salary.setSalarySource(salarySource);
        salary.setAccount(account);

        return salaryRepository.save(salary);
    }

    public List<Salary> findByAccountIdAndYearMonth(Long accountId, YearMonth yearMonth) {
        Account account = accountService.findById(accountId);
        return salaryRepository.findByAccountAndYearMonthOrderByReceivedDate(account, yearMonth);
    }

}