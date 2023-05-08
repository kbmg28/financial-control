package br.com.kbmg.financialcontrol.integration.controller;

import br.com.kbmg.financialcontrol.dto.AccountDto;
import br.com.kbmg.financialcontrol.integration.BaseIntegrationTests;
import builder.AccountBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static builder.AccountBuilder.Constants.USER_NAME;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class AccountIT extends BaseIntegrationTests {
    private static final String ACCOUNT_URL_TEMPLATE = "/api/accounts";

    @Test
    void createAccount_shouldReturnAccountWithId() throws Exception {
        String emailTest = "newEmail@test.com";
        AccountDto accountDto = AccountBuilder.getAccountDto(emailTest);

        whenRequestPost(ACCOUNT_URL_TEMPLATE, accountDto)
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.name").value(USER_NAME))
            .andExpect(jsonPath("$.email").value(emailTest));
    }

    @Test
    void createAccount_shouldReturnBadRequestIfWrongEmail() throws Exception {
        AccountDto accountDto = AccountBuilder.getAccountDto("wrong");
        whenRequestPost(ACCOUNT_URL_TEMPLATE, accountDto)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
