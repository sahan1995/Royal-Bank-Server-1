package lk.royalBank.controller;

import lk.royalBank.dto.EmployeeDTO;
import lk.royalBank.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping(value = "/{id}")
    public void addEmployee(@PathVariable("id") String empID, @RequestBody EmployeeDTO employeeDTO) {
//        employeeService.addEmployee(empID, employeeDTO);
        System.out.println(employeeDTO);
        System.out.println(employeeDTO.getBrachid());
    }

    @GetMapping
    public EmployeeDTO findByID(String empID){
        return new EmployeeDTO();
    }
}
