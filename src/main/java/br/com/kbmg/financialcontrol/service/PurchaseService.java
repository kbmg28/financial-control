package br.com.kbmg.financialcontrol.service;

import br.com.kbmg.financialcontrol.dto.PurchaseCreateDto;
import br.com.kbmg.financialcontrol.enumerators.PurchaseType;
import br.com.kbmg.financialcontrol.mapper.PurchaseMapper;
import br.com.kbmg.financialcontrol.model.Account;
import br.com.kbmg.financialcontrol.model.Card;
import br.com.kbmg.financialcontrol.model.Expense;
import br.com.kbmg.financialcontrol.model.Purchase;
import br.com.kbmg.financialcontrol.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public record PurchaseService(PurchaseRepository purchaseRepository,
                              AccountService accountService,
                              CardService cardService,
                              ExpenseService expenseService) {

    public Purchase createPurchase(Long accountId, PurchaseCreateDto purchaseCreateDto) {
        Account account = accountService.findById(accountId);
        Card card = cardService.findById(purchaseCreateDto.getCard().getId());
        Purchase purchase = PurchaseMapper.INSTANCE.toPurchase(purchaseCreateDto);

        purchaseRepository.save(purchase);

        if (purchaseCreateDto.getType() == PurchaseType.PAID_AT_SIGHT) {
            this.processPaidAtSight(purchase, account, purchaseCreateDto);
        } else {
            this.processInstallments(purchase, account, card, purchaseCreateDto);
        }

        return purchase;
    }

    private void processPaidAtSight(Purchase purchase, Account account, PurchaseCreateDto purchaseCreateDto) {
        Expense expense = expenseService.addSingle(purchase, account, purchaseCreateDto.getTotalValue(), purchaseCreateDto.getDateOfPurchase());
        purchase.getExpenseList().add(expense);
    }

    private void processInstallments(Purchase purchase, Account account, Card card, PurchaseCreateDto purchaseCreateDto) {
        Set<Expense> expenseList = expenseService.addInstallments(purchase, account, card, purchaseCreateDto);
        purchase.setExpenseList(expenseList);
    }

}