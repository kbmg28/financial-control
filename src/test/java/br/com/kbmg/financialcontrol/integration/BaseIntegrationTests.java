package br.com.kbmg.financialcontrol.integration;

import br.com.kbmg.financialcontrol.config.AppConfig;
import com.google.gson.Gson;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Tag("integrationTest")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        properties = {
        "spring.datasource.url=jdbc:h2:file:~/financial-control-JUNIT",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@ContextConfiguration(classes = { AppConfig.class })
@AutoConfigureMockMvc
@Transactional
public abstract class BaseIntegrationTests {
    protected static String testJsonRequest;
    protected static HttpHeaders headers = new HttpHeaders();
    protected static Gson gson = new Gson();

    protected static ResultActions perform;
    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    protected MockMvc mockMvc;

    protected ResultActions whenRequestGet(String urlTemplate) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(urlTemplate)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
    }


    protected <T> ResultActions whenRequestPost(String urlTemplate, T body) throws Exception {
        testJsonRequest = gson.toJson(body);

        return mockMvc.perform(MockMvcRequestBuilders.post(urlTemplate)
                .headers(headers)
                .content(testJsonRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print());
    }

}
