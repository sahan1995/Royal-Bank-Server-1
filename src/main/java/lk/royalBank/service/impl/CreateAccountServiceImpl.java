package lk.royalBank.service.impl;

import lk.royalBank.dto.ClientDTO;
import lk.royalBank.dto.CreateAccountDTO;
import lk.royalBank.dto.UserDTO;
import lk.royalBank.service.BankAccountService;
import lk.royalBank.service.ClientService;
import lk.royalBank.service.CreateAccountService;
import lk.royalBank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateAccountServiceImpl implements CreateAccountService {
    @Autowired
    ClientService  clientService;
    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    UserService userService;
    @Override
    public void createAccount(CreateAccountDTO createAccountDTO) {

        clientService.addClient(createAccountDTO.getClientDTO().getClientID(),createAccountDTO.getClientDTO());
        bankAccountService.addBankAccount(createAccountDTO.getBankAccountDTO().getAccountNumber(),createAccountDTO.getBankAccountDTO());
        ClientDTO clientDTO = createAccountDTO.getClientDTO();
        UserDTO userDTO = new UserDTO(clientDTO.getUserName(),clientDTO.getPassword(),"client",clientDTO.getClientID());
        userService.addUser(clientDTO.getUserName(),userDTO);
}
}
