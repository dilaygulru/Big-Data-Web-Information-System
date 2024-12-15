package org.example.hadoopproject.Controllers;

import jakarta.validation.Valid;
import org.example.hadoopproject.Models.Department;
import org.example.hadoopproject.Models.Employee;
import org.example.hadoopproject.Services.DepartmentService;
import org.example.hadoopproject.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/employee/index")
    public String GetAllEmployees(Model model){
        List<Employee> employees = employeeService.getAllEmployee();
        model.addAttribute("employees",employees);
        return "list-employee";
    }

    @GetMapping("/employee/add")
    public String AddEmployee(Employee employee,Model model){
        List<Department> departments = departmentService.getAllDepartment();
        model.addAttribute("departments",departments);
        model.addAttribute("employee",employee);
        return "add-employee";
    }

    @PostMapping("/employee/add")
    public String AddEmployee(@Valid Employee employee, BindingResult result, Model model, @RequestParam("file")MultipartFile file) throws IOException {
        employeeService.saveEmployee(employee,file);
        return "redirect:/employee/index";
    }

    @GetMapping("/employee/update/{id}")
    public String UpdateEmployee(@PathVariable("id") int id, Model model){
        Employee employee = employeeService.getEmployeeById(id).get();
        List<Department> departments = departmentService.getAllDepartment();
        model.addAttribute("departments",departments);
        model.addAttribute("updatedEmployee",employee);
        return "update-employee";
    }

    @PostMapping("/employee/update/{id}")
    public String UpdateEmployee(@PathVariable("id") int id,Employee employee, @RequestParam("file")MultipartFile file) throws IOException {
        employeeService.updateEmployee(id,employee,file);
        return "redirect:/employee/index";
    }

    @GetMapping("/employee/delete/{id}")
    public String DeleteEmployee(@PathVariable("id") int id){
        employeeService.deleteEmployee(id);
        return "redirect:/employee/index";
    }

}
