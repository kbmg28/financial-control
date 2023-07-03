package br.com.kbmg.financialcontrol.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FinancialControlException extends RuntimeException {
    private final HttpStatus httpStatus;

    public FinancialControlException() {
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public FinancialControlException(String message) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public FinancialControlException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
