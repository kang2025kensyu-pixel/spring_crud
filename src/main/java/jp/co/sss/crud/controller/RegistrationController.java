package jp.co.sss.crud.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.Department;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.repository.DepartmentRepository;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class RegistrationController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;


    @GetMapping("/regist/input")
    public String showRegistrationForm(Model model) {
        model.addAttribute("employee", new EmployeeBean());
        List<Department> departments = departmentRepository.findAll(); 
        model.addAttribute("departments", departments); 
        return "/regist/regist_input";
    }
    
    
    @PostMapping("/regist/check") 
    public String completeRegistration(@ModelAttribute("employee") EmployeeBean employeeBean, Model model) {
        Employee employee = new Employee();

        Optional<Department> deptOptional = departmentRepository.findById(employeeBean.getDeptId());
        if (deptOptional.isPresent()) {
            employee.setDepartment(deptOptional.get());
        } else {
           
            return "error1";
        }

        try {
           
        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            employee.setBirthday(dateFormat.parse(employeeBean.getBirthday()));
        } catch (ParseException e) {
            e.printStackTrace();
            return "error1"; 
        }

        employee.setEmpPass(employeeBean.getEmpPass());
        employee.setEmpName(employeeBean.getEmpName());
        employee.setGender(employeeBean.getGender());
        employee.setAddress(employeeBean.getAddress());
        employee.setAuthority(employeeBean.getAuthority());
      
        
        model.addAttribute("employee", employee);

        return "/regist_check";
    }
    
 
    @PostMapping("/regist/complete")
    public String complete(@ModelAttribute("employee") EmployeeBean employeeBean, Model model) {
        Employee employee = new Employee();

        Optional<Department> deptOptional = departmentRepository.findById(employeeBean.getDeptId());
        if (deptOptional.isPresent()) {
            employee.setDepartment(deptOptional.get());
        } else {
            return "error1";
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            employee.setBirthday(dateFormat.parse(employeeBean.getBirthday()));
        } catch (ParseException e) {
            e.printStackTrace();
            return "error1";
        }

        employee.setEmpPass(employeeBean.getEmpPass());
        employee.setEmpName(employeeBean.getEmpName());
        employee.setGender(employeeBean.getGender());
        employee.setAddress(employeeBean.getAddress());
        employee.setAuthority(employeeBean.getAuthority());

        employeeRepository.save(employee);

        return "/regist_complete";
    }
    
    @GetMapping("/regist/regist_check")
    public String showForm(Model model) {
        return "/regist_check";
    }
}
