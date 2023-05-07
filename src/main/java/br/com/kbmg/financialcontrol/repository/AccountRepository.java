package br.com.kbmg.financialcontrol.repository;

import br.com.kbmg.financialcontrol.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}