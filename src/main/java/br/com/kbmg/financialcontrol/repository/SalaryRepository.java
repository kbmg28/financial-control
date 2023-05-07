package br.com.kbmg.financialcontrol.repository;

import br.com.kbmg.financialcontrol.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

}