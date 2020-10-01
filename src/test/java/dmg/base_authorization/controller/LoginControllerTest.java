package dmg.base_authorization.controller;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static dmg.base_authorization.controller.Page.LOGIN;
import static dmg.base_authorization.controller.Page.MAIN;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class LoginControllerTest extends AbstractController {

    @Test
    void contextLoads() throws Exception {
        mockMvc.perform(get("/" + LOGIN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Sign In")));
    }

    @Test
    @Sql(value = {"/init-user-db-test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void correctLoginTest() throws Exception {
        mockMvc.perform(formLogin().user("TestUser").password("123"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/" + MAIN));
    }

    @Test
    void incorrectLogin() throws Exception {
       mockMvc.perform(formLogin().user("invalidUser").password("invalidPassword"))
               .andDo(print())
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/" + LOGIN + "?error"));
    }
}
