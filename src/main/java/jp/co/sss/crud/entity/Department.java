package jp.co.sss.crud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
	@Id
	private Integer deptId;

	@Column
	private String deptName;

	public void setDeptId(Integer deptId2) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void setDeptId(Integer deptId2) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
