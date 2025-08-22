package jp.co.sss.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import jp.co.sss.crud.bean.EmployeeBean;

@Controller
public class RegistrationController {


    @GetMapping("regist/regist_input")
    public String returnToRegistrationForm(@ModelAttribute("employee") EmployeeBean employee) {
        return "regist/regist_input";
    }
}