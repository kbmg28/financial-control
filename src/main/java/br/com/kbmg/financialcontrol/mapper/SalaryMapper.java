package br.com.kbmg.financialcontrol.mapper;

import br.com.kbmg.financialcontrol.dto.SalaryDto;
import br.com.kbmg.financialcontrol.dto.SalarySourceDto;
import br.com.kbmg.financialcontrol.model.Salary;
import br.com.kbmg.financialcontrol.model.SalarySource;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SalaryMapper {

    SalaryMapper INSTANCE = Mappers.getMapper(SalaryMapper.class);

    SalarySourceDto toSalarySourceDto(SalarySource salarySource);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "salaryList", ignore = true)
    SalarySource toSalarySource(SalarySourceDto salarySourceDto);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "salarySource", ignore = true)
    @Mapping(target = "account", ignore = true)
    Salary toSalary(SalaryDto salaryDto);

    SalaryDto toSalaryDto(Salary salary);

    @IterableMapping(elementTargetType = SalaryDto.class)
    List<SalaryDto> toSalaryDtoList(List<Salary> salaries);
}
