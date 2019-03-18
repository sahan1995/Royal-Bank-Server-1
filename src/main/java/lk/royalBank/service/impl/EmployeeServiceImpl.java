package lk.royalBank.service.impl;

import lk.royalBank.dto.EmployeeDTO;
import lk.royalBank.entity.Branch;
import lk.royalBank.entity.Employee;
import lk.royalBank.repository.EmployeeRepository;
import lk.royalBank.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
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
        employeeRepository.save(employee);

    }
}
