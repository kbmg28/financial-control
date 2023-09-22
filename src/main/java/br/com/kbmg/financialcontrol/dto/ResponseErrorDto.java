package br.com.kbmg.financialcontrol.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseErrorDto {
    @Schema(example = "400 BAD_REQUEST")
    private final String httpStatus;

    @Schema(example = "error.message.key.example")
    private final String messageKey;

    public ResponseErrorDto(HttpStatus http, String messageKey) {
        this.httpStatus = http.toString();
        this.messageKey = messageKey;
    }
}
