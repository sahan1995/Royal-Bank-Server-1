package lk.royalBank.service;

import lk.royalBank.dto.UserDTO;

public interface UserService {

    void addUser(String userName, UserDTO userDTO);
}
