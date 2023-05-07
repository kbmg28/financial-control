package br.com.kbmg.financialcontrol.repository;

import br.com.kbmg.financialcontrol.model.SalarySource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalarySourceRepository extends JpaRepository<SalarySource, Long> {

}