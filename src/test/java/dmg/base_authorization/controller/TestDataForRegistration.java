package dmg.base_authorization.controller;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class TestDataForRegistration {

    static MultiValueMap<String, String> getNewUserParams() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("confirmPassword", "123");
        params.add("password", "123");
        params.add("email", "mail@mail.com");
        params.add("name", "newUser");
        return params;
    }

    static MultiValueMap<String, String> getExistsUserParams() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("confirmPassword", "123");
        params.add("password", "123");
        params.add("email", "user@mail.ru");
        params.add("name", "TestUser");
        return params;
    }

    static MultiValueMap<String, String> getUserWithInvalidParams() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("confirmPassword", "12345");
        params.add("password", "123");
        params.add("email", "mailmail.com");
        params.add("name", "U");
        return params;
    }
}
