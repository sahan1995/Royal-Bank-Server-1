package lk.royalBank.service;

import lk.royalBank.dto.ClientDTO;

public interface ClientService {
    void addClient(String clientID, ClientDTO clientDTO);

    ClientDTO findByID(String clientID);
}
