package org.example.hadoopproject.Services;

import org.example.hadoopproject.Models.Employee;
import org.example.hadoopproject.Models.Department;
import org.example.hadoopproject.Repositories.DepartmentRepository;
import org.example.hadoopproject.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private HdfsService hdfsService;

    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(int id){ return employeeRepository.findById(id);}

    public void saveEmployee(Employee employee, MultipartFile file) throws IOException {
        String localFilePath = System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename();
        File localFile = new File(localFilePath);
        try {
            file.transferTo(localFile);
            String hdfsPath = "/images/" + file.getOriginalFilename();
            hdfsService.uploadFile(localFilePath, hdfsPath);
            //employee.setImg(hdfsService.getFileUrl(file.getOriginalFilename()));
            employee.setImg(file.getOriginalFilename());
        }catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Dosya yüklemesi sırasında hata oluştu.", e);
        }
        employeeRepository.save(employee);
    }

    public Employee updateEmployee(int id ,Employee employee,MultipartFile file) throws IOException {
        Employee getExistEmployee = employeeRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Employee not found with id " + id)
        );

        getExistEmployee.setEmpName(employee.getEmpName());
        getExistEmployee.setJob(employee.getJob());
        getExistEmployee.setMgr(employee.getMgr());
        getExistEmployee.setHireDate(employee.getHireDate());
        getExistEmployee.setSalary(employee.getSalary());
        getExistEmployee.setComm(employee.getComm());
        getExistEmployee.setDepartment(employee.getDepartment());

        if(file!=null && !file.isEmpty()){
            String localFilePath = System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename();
            File localFile = new File(localFilePath);
            try {
                file.transferTo(localFile);
                String hdfsPath = "/images/" + file.getOriginalFilename();
                hdfsService.uploadFile(localFilePath, hdfsPath);
                //employee.setImg(hdfsService.getFileUrl(file.getOriginalFilename()));
                getExistEmployee.setImg(file.getOriginalFilename());
            }catch (IOException e) {
                e.printStackTrace();
                throw new IOException("Dosya yüklemesi sırasında hata oluştu.", e);
            }
        }else{
            getExistEmployee.setImg(getExistEmployee.getImg());
        }

        return employeeRepository.save(getExistEmployee);
    }

    public void deleteEmployee(int id){
        Employee getExistEmployee = employeeRepository.findById(id).get();
        employeeRepository.delete(getExistEmployee);
    }
}
