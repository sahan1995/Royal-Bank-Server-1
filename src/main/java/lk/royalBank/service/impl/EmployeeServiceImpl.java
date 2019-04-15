package lk.royalBank.service.impl;

import lk.royalBank.dto.BranchDTO;
import lk.royalBank.dto.EmployeeDTO;
import lk.royalBank.dto.UserDTO;
import lk.royalBank.entity.Branch;
import lk.royalBank.entity.Employee;
import lk.royalBank.repository.EmployeeRepository;
import lk.royalBank.service.BranchService;
import lk.royalBank.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BranchService branchService;
//    @Autowired
//    private UserService userService;
    @Override
    public void addEmployee(String empID, EmployeeDTO employeeDTO) {
        if(!empID.equals(employeeDTO.getEmpID())){
            throw  new RuntimeException("ID's are not same");
        }


        Employee employee = new Employee();
        Branch branch = new Branch();
        BranchDTO branchDTO = branchService.findByID(employeeDTO.getBrachid());

        BeanUtils.copyProperties(employeeDTO,employee);
        BeanUtils.copyProperties(branchDTO,branch);
        employee.setBranch(branch);
        employeeRepository.save(employee);

        RestTemplate restTemplate = new RestTemplate();
        try{
            UserDTO userDTO = new UserDTO(employeeDTO.getUserName(),employeeDTO.getPassword(),employeeDTO.getRole(),employeeDTO.getEmpID());
            ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://192.168.1.101:8082/api/v1/users/"+employeeDTO.getUserName(), userDTO, null);
        }catch (Exception e){
            UserDTO userDTO = new UserDTO(employeeDTO.getUserName(),employeeDTO.getPassword(),employeeDTO.getRole(),employeeDTO.getEmpID());
            ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://192.168.1.101:8083/api/v1/users/"+employeeDTO.getUserName(), userDTO, null);


        }


    }

    @Override
    public EmployeeDTO findBYID(String empID) {
        Optional<Employee> getEmp = employeeRepository.findById(empID);
        if(getEmp.isPresent()){
            Employee employee = getEmp.get();
            EmployeeDTO employeeDTO = new EmployeeDTO();
            BeanUtils.copyProperties(employee,employeeDTO);
            return employeeDTO;
        }
        throw new RuntimeException("User Not Found !");


    }

}
