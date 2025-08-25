package jp.co.sss.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.crud.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	List<Department> findBy(Integer depId);


//Department dept = Department.findById(Employee.getDeptId());

}
