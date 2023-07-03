package br.com.kbmg.financialcontrol.mapper;

import br.com.kbmg.financialcontrol.dto.PurchaseCreateDto;
import br.com.kbmg.financialcontrol.dto.PurchaseDto;
import br.com.kbmg.financialcontrol.model.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PurchaseMapper {

    PurchaseMapper INSTANCE = Mappers.getMapper(PurchaseMapper.class);

    PurchaseDto toPurchaseDto(Purchase purchase);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "expenseList", ignore = true)
    Purchase toPurchase(PurchaseCreateDto purchaseCreateDto);

}
