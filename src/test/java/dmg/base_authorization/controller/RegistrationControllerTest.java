package dmg.base_authorization.controller;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static dmg.base_authorization.controller.Page.REGISTRATION;
import static dmg.base_authorization.controller.TestDataForRegistration.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RegistrationControllerTest extends AbstractController {
    private static final String URL_PATH = "/" + REGISTRATION;

    @Test
    void contextLoads() throws Exception {
        mockMvc.perform(get(URL_PATH))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Register")));
    }

    @Test
    void correctRegistration() throws Exception {
        mockMvc.perform(post(URL_PATH).params(getNewUserParams()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().errorCount(0))
                .andExpect(content().string(containsString("Log in")));
    }

    @Test
    @Sql(value = {"/init-user-db-test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void incorrectRegistrationWithExistsUser() throws Exception {
        mockMvc.perform(post(URL_PATH).params(getExistsUserParams()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(content().string(containsString("Such user already exists.")));
    }

    @Test
    void incorrectRegistrationWithInvalidData() throws Exception {
        mockMvc.perform(post(URL_PATH).params(getUserWithInvalidParams()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(3))
                .andExpect(content().string(containsString("Must be in email format.")))
                .andExpect(content().string(containsString("Name must be at least 2 characters.")))
                .andExpect(content().string(containsString("The password fields must match.")))
                .andExpect(content().string(containsString("Register")));
    }
}