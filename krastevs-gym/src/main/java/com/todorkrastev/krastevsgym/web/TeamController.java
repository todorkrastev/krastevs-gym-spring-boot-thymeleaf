package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.model.dto.EmployeeDetailsDTO;
import com.todorkrastev.krastevsgym.model.dto.EmployeesShortInfoDTO;
import com.todorkrastev.krastevsgym.service.EmployeesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/team")
public class TeamController {

    private final EmployeesService employeesService;

    public TeamController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }


    @GetMapping("/all-employees")
    public String allEmployees(Model model) {
        List<EmployeesShortInfoDTO> employeesServiceAll = employeesService.findAll();
        model.addAttribute("employees", employeesServiceAll);

        return "team";
    }

    @GetMapping("/employee/{id}")
    public String employeeDetails(@PathVariable Long id, Model model) {
        EmployeeDetailsDTO employeeDetailsDTO = employeesService.findById(id);
        model.addAttribute("employee", employeeDetailsDTO);

        return "team-employee";
    }
}
