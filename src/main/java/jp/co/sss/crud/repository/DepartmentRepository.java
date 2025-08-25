package jp.co.sss.crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.crud.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {


	 List<Department> findByDeptId(Integer deptId); // 複数件取得したい場合
	 Optional<Department> findById(Integer deptId); // 主キーで1件取得したい場合

//Department dept = Department.findById(Employee.getDeptId());

}
