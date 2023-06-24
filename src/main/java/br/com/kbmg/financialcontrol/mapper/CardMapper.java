package br.com.kbmg.financialcontrol.mapper;

import br.com.kbmg.financialcontrol.dto.CardDto;
import br.com.kbmg.financialcontrol.model.Card;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CardMapper {

    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    CardDto toCardDto(Card card);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", ignore = true)
    Card toCard(CardDto cardDto);

    @IterableMapping(elementTargetType = CardDto.class, qualifiedByName = "toCardDto")
    List<CardDto> toCardDtoList(List<Card> cards);
}
