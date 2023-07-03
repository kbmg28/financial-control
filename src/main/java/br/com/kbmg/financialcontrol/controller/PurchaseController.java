package br.com.kbmg.financialcontrol.controller;

import br.com.kbmg.financialcontrol.dto.PurchaseCreateDto;
import br.com.kbmg.financialcontrol.dto.PurchaseDto;
import br.com.kbmg.financialcontrol.mapper.PurchaseMapper;
import br.com.kbmg.financialcontrol.model.Purchase;
import br.com.kbmg.financialcontrol.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<PurchaseDto> createPurchase(@RequestAttribute(required = false) Long accountId,
                                                      @Valid @RequestBody PurchaseCreateDto purchaseCreateDto) {
        Purchase purchase = purchaseService.createPurchase(accountId, purchaseCreateDto);
        PurchaseDto purchaseDto = PurchaseMapper.INSTANCE.toPurchaseDto(purchase);
        return new ResponseEntity<>(purchaseDto, HttpStatus.CREATED);
    }
}
