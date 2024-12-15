package org.example.hadoopproject.Controllers;

import org.example.hadoopproject.Models.Department;
import org.example.hadoopproject.Services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/department/index")
    public String GetAllDepartment(Model model){
        List<Department> departments = departmentService.getAllDepartment();
        model.addAttribute("departments",departments);
        return "list-department";
    }

    @GetMapping("/department/add")
    public String AddDepartment(Model model){
        Department department = new Department();
        model.addAttribute("department",department);
        return "add-department";
    }

    @PostMapping("/department/add")
    public String AddDepartment(Department department){
        departmentService.addDepartment(department);
        return "redirect:/department/index";
    }

    @GetMapping("/department/update/{id}")
    public String UpdateDepartment(@PathVariable("id") int id, Model model){
        Department existDepartment = departmentService.getDepartmentById(id);
        model.addAttribute("department",existDepartment);
        return "update-department";
    }

    @PostMapping("/department/update/{id}")
    public String UpdateDepartment(@PathVariable("id") int id, Department department){
        departmentService.updateDepartment(id,department);
        return "redirect:/department/index";
    }

    @GetMapping("/department/delete/{id}")
    public String DeleteDepartment(@PathVariable("id") int id){
        departmentService.deleteDepartment(id);
        return "redirect:/department/index";
    }
}
