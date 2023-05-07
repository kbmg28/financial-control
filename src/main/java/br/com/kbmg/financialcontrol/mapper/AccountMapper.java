package br.com.kbmg.financialcontrol.mapper;

import br.com.kbmg.financialcontrol.dto.AccountDto;
import br.com.kbmg.financialcontrol.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto toDto(Account account);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "salaryList", ignore = true)
    @Mapping(target = "purchaseList", ignore = true)
    @Mapping(target = "expenseList", ignore = true)
    Account toEntity(AccountDto accountDto);
}
