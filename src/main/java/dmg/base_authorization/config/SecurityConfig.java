package dmg.base_authorization.config;

import dmg.base_authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import static dmg.base_authorization.controller.Page.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()

                //Доступ только для не зарегистрированных пользователей
                .antMatchers("/"+REGISTRATION).not().fullyAuthenticated()

                //Доступ только для пользователей с ролью ADMIN
                .antMatchers("/admin/**").hasRole("ADMIN")

                //Доступ только для пользователей с ролью USER
                .antMatchers("/user/**").hasRole("USER")

                //Доступ разрешен всем пользователей
                .antMatchers("/", "/resources/**").permitAll()

                //Все остальные страницы требуют аутентификации
                .anyRequest().authenticated()
                .and()

                //Настройка для входа в систему
                .formLogin()
                .loginPage("/" + LOGIN)

                //Перенарпавление на страницу после успешного входа
                .defaultSuccessUrl("/" + MAIN)

                .permitAll()
                .and()
                .logout()
                .permitAll()

                //Перенарпавление на страницу после выхода
                .logoutSuccessUrl("/");
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}
