package org.example.hadoopproject.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empNo;

    @NotEmpty(message = "Employee Name is required")
    private String empName;

    private String job;

    @Nullable
    private Integer mgr;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;

    @Min(value = 0, message = "Salary must be a positive number")
    private int salary;

    @Nullable
    private Integer comm;

    @ManyToOne
    @JoinColumn(name="deptNo",referencedColumnName = "id")
    @JsonBackReference
    private Department department;

    @Nullable
    private String img;

    public Employee() {
    }

    public Employee(int empNo, String empName, String job, int mgr, Date hireDate, int salary, int comm, Department department, String img){
        this.empNo = empNo;
        this.empName = empName;
        this.job = job;
        this.mgr = mgr;
        this.hireDate = hireDate;
        this.salary = salary;
        this.comm = comm;
        this.department = department;
        this.img = img;
    }

    public int getEmpNo(){
        return empNo;
    }

    public void setEmpNo(int empNo){
        this.empNo = empNo;
    }

    public String getEmpName(){
        return empName;
    }
    public void setEmpName(String empName){
        this.empName = empName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Nullable
    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(@Nullable Integer mgr) {
        this.mgr = mgr;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Nullable
    public Integer getComm() {
        return comm;
    }

    public void setComm(@Nullable Integer comm) {
        this.comm = comm;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
