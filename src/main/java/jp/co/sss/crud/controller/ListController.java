package jp.co.sss.crud.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.repository.EmployeeRepository;
@Controller
public class ListController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/list")
    public String getAllEmployees(Model model, HttpSession session) {        
    	List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "list/list"; // 正しいテンプレートパス
    }
}


