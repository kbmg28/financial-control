package builder;

import br.com.kbmg.financialcontrol.dto.AccountDto;
import br.com.kbmg.financialcontrol.model.Account;

import static builder.AccountBuilder.Constants.EMAIL;
import static builder.AccountBuilder.Constants.USER_NAME;

public abstract class AccountBuilder {

    public abstract static class Constants {
        public static final String EMAIL = "junittest@test.com";
        public static final String USER_NAME = "Junit Test";
    }

    public static Account getAccount(){
        Account account = new Account();
        account.setEmail(EMAIL);
        account.setName(USER_NAME);

        return account;
    }

    public static AccountDto getAccountDto(){
        AccountDto accountDto = new AccountDto();
        accountDto.setEmail(EMAIL);
        accountDto.setName(USER_NAME);

        return accountDto;
    }

    public static AccountDto getAccountDto(String email){
        AccountDto accountDto = new AccountDto();
        accountDto.setEmail(email);
        accountDto.setName(USER_NAME);

        return accountDto;
    }

}
