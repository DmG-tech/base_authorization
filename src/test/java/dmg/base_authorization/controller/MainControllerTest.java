package dmg.base_authorization.controller;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static dmg.base_authorization.controller.Page.LOGIN;
import static dmg.base_authorization.controller.Page.MAIN;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql(value = {"/init-user-db-test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class MainControllerTest extends AbstractController {

    @Test
    void mainPage() throws Exception {
        mockMvc.perform(get("/" + MAIN))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("/html/body/h1").string("Hello TestUser!"));
    }

    @Test
    void accessDeniedTest() throws Exception {
        mockMvc.perform(get("/" + MAIN))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/" + LOGIN));
    }

}