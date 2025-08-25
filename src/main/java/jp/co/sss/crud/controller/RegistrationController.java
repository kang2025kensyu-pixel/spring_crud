package jp.co.sss.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.Department;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.EmployeeForm;
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

	@PostMapping("/regist/complet")
	public String completeRegistration(@ModelAttribute("employee") EmployeeForm form, EmployeeBean employeeBean,
			Model model) {
		Employee employee = new Employee();
		Department department = new Department();
		department.setDeptId(form.getDeptId());

		Optional<Department> deptOptional = departmentRepository.findById(form.getDeptId());
		BeanUtils.copyProperties(form, employee);
		if (deptOptional.isPresent()) {
			employee.setDepartment(deptOptional.get());
		} else {
			return "error1"; // 部署が存在しない場合のエラーハンドリング
		}

		//        Optional<Department> deptOptional = departmentRepository.findById(form.getDeptId());
		//        BeanUtils.copyProperties(form,employee);
		//	
		 
		return "redirect:regist/regist_check";
	}
	

	@GetMapping("/regist/regist_check")
	public String showForm(@ModelAttribute("employee") EmployeeBean employee, Model model) {

		model.addAttribute("employee", employee);
		return "/regist/regist_check";
	}

	
	
	@PostMapping("/regist/confirm")
	public String processRegistrationForm(@ModelAttribute("employee") EmployeeForm form, Model model) {
	    Employee employee = new Employee();
	    BeanUtils.copyProperties(form, employee);

	    Optional<Department> deptOptional = departmentRepository.findById(form.getDeptId());
	    if (deptOptional.isPresent()) {
	        employee.setDepartment(deptOptional.get());
	    } else {
	        return "error1";
	    }

	    employeeRepository.save(employee);
	    return "redirect:/regist/complete";
	}


}
