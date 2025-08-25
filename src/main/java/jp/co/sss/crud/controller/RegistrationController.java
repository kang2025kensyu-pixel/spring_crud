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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		// Use EmployeeForm for consistency
		model.addAttribute("employee", new EmployeeForm());
		List<Department> departments = departmentRepository.findAll();
		model.addAttribute("departments", departments);
		return "/regist/regist_input";
	}

	@PostMapping("/regist/check")
	public String checkRegistration(@ModelAttribute("employee") EmployeeForm form, Model model) {
	    Optional<Department> deptOptional = departmentRepository.findById(form.getDeptId());
	    if (deptOptional.isPresent()) {
	        // You've already put the Department object in the model here!
	        model.addAttribute("department", deptOptional.get());
	    } else {
	        return "error1";
	    }
	    return "/regist/regist_check";
	}

	// 3. Handle the final registration (save data to the database).
	@PostMapping("/regist/confirm")
	public String processRegistrationForm(@ModelAttribute("employee") EmployeeForm form, RedirectAttributes redirectAttributes) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(form, employee);

		Optional<Department> deptOptional = departmentRepository.findById(form.getDeptId());
		if (deptOptional.isPresent()) {
			employee.setDepartment(deptOptional.get());
		} else {
			return "error1";
		}

		employeeRepository.save(employee);
		redirectAttributes.addFlashAttribute("message", "社員の登録が完了しました。");

		return "redirect:/regist/complete";
	}

	// 4. Display the registration complete page.
	@GetMapping("/regist/complete")
	public String showRegistrationComplete() {
		return "/regist/regist_complete";
	}

	// 5. Handle the "Return" button on the confirmation page.
	@PostMapping("/regist/return")
	public String returnToInput(@ModelAttribute("employee") EmployeeForm form, RedirectAttributes redirectAttributes) {
		// Pass the form data back to the input page via flash attributes.
		redirectAttributes.addFlashAttribute("employee", form);
		return "redirect:/regist/input";
	}
}