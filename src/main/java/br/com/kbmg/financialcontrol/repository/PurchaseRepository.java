package br.com.kbmg.financialcontrol.repository;

import br.com.kbmg.financialcontrol.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}