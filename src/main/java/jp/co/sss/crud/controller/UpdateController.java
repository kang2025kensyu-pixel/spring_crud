package jp.co.sss.crud.controller;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sss.crud.entity.Department;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.repository.DepartmentRepository;
import jp.co.sss.crud.repository.EmployeeRepository;


@Controller
public class UpdateController {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
  
	@GetMapping("/update/input/{empId}")
	public String showUpdateForm(@PathVariable("empId") Department empId, Model model) {
		Employee employee = new Employee(); 
//		=================================
//				Mon Aug 25 17:51:50 JST 2025
//				There was an unexpected error (type=Internal Server Error, status=500).
//				============================
	//	employeeRepository.setempId(empId);
//		 Optional<Department> deptOpt = departmentRepository.findById(employee.getDepartment(empId));
//		 model.addAttribute("employee", employee); 
		 //	    Optional<Employee> employeeOpt = employeeRepository.findByEmpId(empId);
//	    if (employeeOpt.isPresent()) {
//	        Employee employee = employeeOpt.get();
//	        EmployeeForm form = new EmployeeForm();
//	        BeanUtils.copyProperties(employee, form);
//        List<ItemWithCategory> items = repository.findByCategory(category); 

//	        Optional<Department> deptOpt = departmentRepository.findById(employee.getDepartment(empId));
//	        deptOpt.ifPresent(dept -> model.addAttribute("department", dept));
//
	        model.addAttribute("employee");
	        return "/update/input";
	    }



	@PostMapping("/updateRecord")
	public String updateRecord(@ModelAttribute("employee") EmployeeForm form) {
	    Optional<Employee> employeeOpt = employeeRepository.findByEmpId(form.getEmpId());
	    Optional<Department> deptOpt = departmentRepository.findById(form.getDeptId());
	    if (employeeOpt.isPresent()) {
	        Employee employee = employeeOpt.get();
	        BeanUtils.copyProperties(form, employee);
	        employeeRepository.save(employee);
	        return "redirect:/update/complete";
	    } else {
	        return "error";
	    }
	}
	

 
  

    @PostMapping("/updateRecord/confirm")
    public String updateRecord(@ModelAttribute Model model) {
    
        return "redirect:/list"; 
    }

}