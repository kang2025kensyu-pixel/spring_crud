package jp.co.sss.crud.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils; // Import for String validation
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String showUpdateForm(@PathVariable("empId") Integer empId, Model model) {
        Optional<Employee> employeeOpt = employeeRepository.findByEmpId(empId);
        
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            EmployeeForm form = new EmployeeForm();
            BeanUtils.copyProperties(employee, form);
            form.setDeptId(employee.getDepartment().getDeptId());

            model.addAttribute("employee", form);
            List<Department> departments = departmentRepository.findAll();
            model.addAttribute("departments", departments);

            return "/update/update_input";
        } else {
            return "redirect:/list"; 
        }
    }



    @PostMapping("/update/complete_check")
    public String checkUpdate(@ModelAttribute("employee") EmployeeForm form, Model model) {
        // Validate all required fields
        if (!StringUtils.hasText(form.getEmpName()) || !StringUtils.hasText(form.getEmpPass())
                || !StringUtils.hasText(form.getAddress()) || !StringUtils.hasText(form.getBirthday())) {
            model.addAttribute("error", "全ての項目を入力してください。");
            model.addAttribute("departments", departmentRepository.findAll());
            return "/update/update_input";
        }

     
        try {
            new SimpleDateFormat("yyyy/MM/dd").setLenient(false).parse(form.getBirthday());
        } catch (ParseException e) {
            model.addAttribute("error", "生年月日の形式が正しくありません。(YYYY/MM/DD)");
            model.addAttribute("departments", departmentRepository.findAll());
            return "/update/update_input";
        }

        Optional<Department> deptOptional = departmentRepository.findById(form.getDeptId());
        if (deptOptional.isPresent()) {
            form.setDepartment(deptOptional.get());
            model.addAttribute("employee", form);
            return "/update/update_check";
        } else {
            model.addAttribute("error", "指定された部署が見つかりません。");
            model.addAttribute("departments", departmentRepository.findAll());
            return "/update/update_input";
        }
    }

  

    @PostMapping("/update/complete")
    public String updateRecord(@ModelAttribute("employee") EmployeeForm form, RedirectAttributes redirectAttributes) {
        Optional<Employee> employeeOpt = employeeRepository.findByEmpId(form.getEmpId());
        
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            
            BeanUtils.copyProperties(form, employee, "birthday");
      
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            try {
                Date birthdayDate = formatter.parse(form.getBirthday());
                employee.setBirthday(birthdayDate); 
            } catch (ParseException e) {
                redirectAttributes.addFlashAttribute("error", "生年月日の形式が正しくありません。 (YYYY/MM/DD)");
                return "redirect:/update/input/" + form.getEmpId();
            }

            Optional<Department> deptOpt = departmentRepository.findById(form.getDeptId());
            deptOpt.ifPresent(employee::setDepartment);
        
            employeeRepository.save(employee);
            
            redirectAttributes.addFlashAttribute("message", "社員情報の変更が完了しました。");
            return "redirect:/update/complete_page";
        } else {
            return "redirect:/list";
        }
    }

   

    @GetMapping("/update/complete_page")
    public String showUpdateComplete() {
        return "/update/update_complete";
    }
    
    @PostMapping("/update/return")
    public String returnToInput(@ModelAttribute("employee") EmployeeForm form, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("employee", form);
        return "redirect:/update/input/" + form.getEmpId();
    }
}