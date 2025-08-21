package jp.co.sss.crud.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.LoginForm;
import jp.co.sss.crud.repository.EmployeeRepository;
@Controller
public class IndexController {

    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(@ModelAttribute LoginForm loginForm, HttpSession session) {
        session.invalidate();
        return "index";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String doLoginWithValidation(
            @Valid @ModelAttribute LoginForm form,
            BindingResult result,
            HttpSession session,
            Model model) {

        if (result.hasErrors()) {
            return "index";
        }

        Optional<Employee> employeeOpt = employeeRepository.findByEmpId(form.getEmpId());

        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            if (employee.getEmpPass() != null && employee.getEmpPass().equals(form.getEmpPass())) {
                session.setAttribute("userId", employee.getEmpId());
                return "list/list";
            }
        }

        model.addAttribute("loginError", "社員IDまたはパスワードが正しくありません");
        return "index";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}


