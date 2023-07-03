package br.com.kbmg.financialcontrol.service;

import br.com.kbmg.financialcontrol.dto.PurchaseCreateDto;
import br.com.kbmg.financialcontrol.model.Account;
import br.com.kbmg.financialcontrol.model.Card;
import br.com.kbmg.financialcontrol.model.Expense;
import br.com.kbmg.financialcontrol.model.Purchase;
import br.com.kbmg.financialcontrol.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public record ExpenseService(ExpenseRepository expenseRepository) {

    public Expense addSingle(Purchase purchase, Account account, BigDecimal totalValue, OffsetDateTime dateOfPurchase) {
        Expense expense = new Expense(account, purchase);

        expense.setExpirationDate(dateOfPurchase.toLocalDate());
        expense.setPrice(totalValue);

        expense.setPaymentDate(dateOfPurchase);
        expense.setValuePaid(totalValue);

        return expenseRepository.save(expense);
    }

    public Set<Expense> addInstallments(Purchase purchase,
                                        Account account,
                                        Card card,
                                        PurchaseCreateDto purchaseCreateDto) {
        BigDecimal installmentsBigDecimal = new BigDecimal(purchaseCreateDto.getInstallments());
        BigDecimal valueForEachInstallment = purchaseCreateDto.getTotalValue().divide(installmentsBigDecimal, 2, RoundingMode.HALF_UP);

        Set<Expense> expenseList = new HashSet<>();

        for (int i = 0; i < purchaseCreateDto.getInstallments(); i++) {
            Expense expense = new Expense(account, purchase);

            expense.setInstallment(i+1);
            expense.setPrice(valueForEachInstallment);
            expense.setExpirationDate(card.getPaymentDate());

            expenseList.add(expenseRepository.save(expense));
        }

        return expenseList;
    }
}