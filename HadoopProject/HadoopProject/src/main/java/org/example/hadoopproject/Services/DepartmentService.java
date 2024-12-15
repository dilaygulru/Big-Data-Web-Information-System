package org.example.hadoopproject.Services;

import org.example.hadoopproject.Models.Department;
import org.example.hadoopproject.Repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department getDepartmentById(int id){
        return departmentRepository.findById(id).get();
    }

    public List<Department> getAllDepartment(){
        return departmentRepository.findAll();
    }

    public void addDepartment(Department department){
        departmentRepository.save(department);
    }

    public Department updateDepartment(int id, Department department){
        Department getExistDepartment = departmentRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Employee not found with id " + id)
        );

        getExistDepartment.setDeptName(department.getDeptName());
        getExistDepartment.setIoc(department.getIoc());

        return departmentRepository.save(getExistDepartment);
    }

    public void deleteDepartment(int id){
        departmentRepository.deleteById(id);
    }

}
