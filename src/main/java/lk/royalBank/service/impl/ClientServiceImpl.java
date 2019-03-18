package lk.royalBank.service.impl;

import lk.royalBank.dto.ClientDTO;
import lk.royalBank.entity.Client;
import lk.royalBank.repository.ClientRepository;
import lk.royalBank.service.BankAccountService;
import lk.royalBank.service.ClientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    BankAccountService bankAccountService;
    @Override
    public void addClient(String clientID, ClientDTO clientDTO) {
        if(!clientID.equals(clientDTO.getClientID())){
            throw new RuntimeException("ID's are Not Same");
        }
        Client client = new Client();
        BeanUtils.copyProperties(clientDTO,client);
        clientRepository.save(client);
    }
}
