package br.com.kbmg.financialcontrol.repository;

import br.com.kbmg.financialcontrol.model.Account;
import br.com.kbmg.financialcontrol.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

    List<Salary> findByAccountAndYearMonthOrderByReceivedDate(Account account, YearMonth yearMonth);
}