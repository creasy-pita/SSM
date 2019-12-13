package org.creasypita.User.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by creasypita on 12/13/2019.
 *
 * @ProjectName: SSM
 */
@Controller
public class HelloController {

    @RequestMapping(value = "hello")
    public String inputEmployee(Model model) {
        model.addAttribute("info", "hello my name is creasypita");
        return "hello";
    }
}
