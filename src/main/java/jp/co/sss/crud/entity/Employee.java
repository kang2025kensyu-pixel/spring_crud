package jp.co.sss.crud.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jp.co.sss.crud.form.EmployeeForm;

@Entity
@Table(name = "employee")
public class Employee {



	@Id
	private Integer empId;

	@Column
	private String empPass;

	@Column
	private String empName;

	@Column
	private Integer gender;

	@Column
	private String address;

	@Column
	private Date birthday;

	@Column
	private Integer authority;

	@ManyToOne
	@JoinColumn(name = "dept_id")
	private Department department;

	public void setEmpId(Integer empId2) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void setEmpPass(String empPass2) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void setEmpName(String empName2) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void setGender(Integer gender2) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void setAddress(String address2) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void setBirthday(Date birthday2) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void setAuthority(Integer authority2) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void setDepartment(Department department) {
		// TODO 自動生成されたメソッド・スタブ
		
	}



	public String getEmpName() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public Integer getGender() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public String getAddress() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public Date getBirthday() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public Integer getAuthority() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public EmployeeForm getDepartment() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public Object getEmpPass() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public Object getEmpId() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
