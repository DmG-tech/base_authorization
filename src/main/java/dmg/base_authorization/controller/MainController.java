package dmg.base_authorization.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static dmg.base_authorization.controller.Page.MAIN;

@Controller
@RequestMapping(value = "/" + MAIN)
public class MainController {

    @GetMapping
    public String getPage() {
        return MAIN;
    }
}
