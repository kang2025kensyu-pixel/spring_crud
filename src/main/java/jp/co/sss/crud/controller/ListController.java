package jp.co.sss.crud.controller;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.repository.EmployeeRepository;


@Controller
@RequestMapping("/employee")

public class ListController {
	@Autowired
	EmployeeRepository employeeRepository;

    @GetMapping("/list")
    public String getAllEmployees(Model model) {
        List<EmployeeBean> employees = employeeRepository.findAll(); 
        model.addAttribute("employees", employees); 

        return "/list"; 
    }

}
