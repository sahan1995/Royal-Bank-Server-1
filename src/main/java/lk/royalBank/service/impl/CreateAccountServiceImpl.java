package lk.royalBank.service.impl;

import lk.royalBank.dto.ClientDTO;
import lk.royalBank.dto.CreateAccountDTO;
import lk.royalBank.dto.UserDTO;
import lk.royalBank.service.BankAccountService;
import lk.royalBank.service.ClientService;
import lk.royalBank.service.CreateAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class CreateAccountServiceImpl implements CreateAccountService {
    @Autowired
    ClientService  clientService;
    @Autowired
    BankAccountService bankAccountService;


    @Override
    public void createAccount(CreateAccountDTO createAccountDTO) {

        clientService.addClient(createAccountDTO.getClientDTO().getClientID(),createAccountDTO.getClientDTO());
        bankAccountService.addBankAccount(createAccountDTO.getBankAccountDTO().getAccountNumber(),createAccountDTO.getBankAccountDTO());
        ClientDTO clientDTO = createAccountDTO.getClientDTO();
//        UserDTO userDTO = new UserDTO(clientDTO.getUserName(),clientDTO.getPassword(),"client",clientDTO.getClientID());

        RestTemplate restTemplate = new RestTemplate();
        try{
            System.out.println("HERE");
            UserDTO userDTO = new UserDTO(clientDTO.getUserName(),clientDTO.getPassword(),"Client",clientDTO.getClientID());
            System.out.println(userDTO);
            HttpStatus statusCode = restTemplate.postForEntity("http://192.168.1.101:8082/api/v1/users/" + clientDTO.getUserName(), userDTO, null).getStatusCode();
            System.out.println(statusCode);
        }catch (Exception e){
//            e.printStackTrace();

            UserDTO userDTO = new UserDTO(clientDTO.getUserName(),clientDTO.getPassword(),"Client",clientDTO.getClientID());
            ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://192.168.1.101:8083/api/v1/users/"+clientDTO.getUserName(), userDTO, null);


        }

}
}
