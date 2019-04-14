package lk.royalBank.service.impl;

import lk.royalBank.dto.EmployeeDTO;
import lk.royalBank.dto.UserDTO;
import lk.royalBank.entity.Branch;
import lk.royalBank.entity.Employee;
import lk.royalBank.repository.EmployeeRepository;
import lk.royalBank.service.EmployeeService;
import lk.royalBank.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserService userService;
    @Override
    public void addEmployee(String empID, EmployeeDTO employeeDTO) {
        if(!empID.equals(employeeDTO.getEmpID())){
            throw  new RuntimeException("ID's are not same");
        }
        Employee employee = new Employee();
        Branch branch = new Branch();
        BeanUtils.copyProperties(employeeDTO,employee);
        BeanUtils.copyProperties(employeeDTO.getBranchDTO(),branch);
        employee.setBranch(branch);
        UserDTO userDTO = new UserDTO(employeeDTO.getUserName(),employeeDTO.getPassword(),employeeDTO.getRole(),employeeDTO.getEmpID());
        userService.addUser(employeeDTO.getUserName(),userDTO);
        employeeRepository.save(employee);

    }
}
