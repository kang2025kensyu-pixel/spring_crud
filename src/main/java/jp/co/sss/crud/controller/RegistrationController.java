//package jp.co.sss.crud.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//
//import jp.co.sss.crud.bean.EmployeeBean;
//
//@Controller
//public class RegistrationController {
//
//
//    @GetMapping("regist/regist_input")
//    public String returnToRegistrationForm(@ModelAttribute("employee") EmployeeBean employee) {
//        return "regist/regist_input";
//    }
//    
//}
package jp.co.sss.crud.controller;

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
	// 登録フォーム表示
    @GetMapping("/registration")
    public String returnToRegistrationForm( Model model) {
        model.addAttribute("employee",  new EmployeeBean());
        return "regist_input";
    }

    //  入力確認画面へ
    @PostMapping("/registration/regist_check")
    public String postRegistration(@ModelAttribute("employee") EmployeeBean employee, Model model) {
        model.addAttribute("employee", employee);
        return "regist_check";
    }
    
    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping("/registration/complete")
    public String completeRegistration(@ModelAttribute("employee") EmployeeBean employeeBean) {
        Employee employee = new Employee();

        // 部署IDから部署エンティティを取得
        Department dept = departmentRepository.findById(employeeBean.getDeptId()).orElse(null);
        employee.setDepartment(dept);

        // その他のフィールド設定
        employee.setEmpName(employeeBean.getEmpName());
        employee.setEmpPass(employeeBean.getEmpPass());
        // ...

        employeeRepository.save(employee);
        return "regist_complete";
    }
}