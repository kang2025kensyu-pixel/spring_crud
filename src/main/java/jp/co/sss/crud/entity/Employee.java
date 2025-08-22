package jp.co.sss.crud.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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


	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpPass() {
		return empPass;
	}

	public void setEmpPass(String empPass) {
		this.empPass = empPass;
	}

	public void setEmpName(String empName) {
		// TODO 自動生成されたメソッド・スタブ
		this.empName = empName;
		
	}

	public String getEmpName() {
		// TODO 自動生成されたメソッド・スタブ
		return empName;
	}

	public Integer getGender() {
		// TODO 自動生成されたメソッド・スタブ
		return gender;
	}

	public String getAddress() {
		// TODO 自動生成されたメソッド・スタブ
		return address;
	}

	public Date getBirthday() {
		// TODO 自動生成されたメソッド・スタブ
		return birthday;
	}

	public Integer getAuthority() {
		// TODO 自動生成されたメソッド・スタブ
		return authority;
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

//	public Department getDepartment() {
//		// TODO 自動生成されたメソッド・スタブ
//		return null;
//	}



	



	

//	@ManyToOne
//	@JoinColumn(name = "dept_id");
	
}
