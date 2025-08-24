package jp.co.sss.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.crud.entity.Department;
import jp.co.sss.crud.entity.Employee;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

Department dept = Department.findById(Employee.getDeptId()).orElse(null);

}
