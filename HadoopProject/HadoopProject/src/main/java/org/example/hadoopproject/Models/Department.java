package org.example.hadoopproject.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="departments")
public class Department {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String deptName;
    private String Ioc;

    @OneToMany(mappedBy = "department")
    @JsonManagedReference
    private List<Employee> employees;

    public Department() {
    }

    public Department(int id, String deptName, String Ioc) {
        this.id = id;
        this.deptName = deptName;
        this.Ioc = Ioc;
    }

    public int getDeptNo() {
        return id;
    }

    public void setDeptNo(int id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getIoc() {
        return Ioc;
    }

    public void setIoc(String ioc) {
        Ioc = ioc;
    }
}
