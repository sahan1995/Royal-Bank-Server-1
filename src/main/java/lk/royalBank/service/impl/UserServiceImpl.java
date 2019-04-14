package lk.royalBank.service.impl;

import lk.royalBank.dto.UserDTO;
import lk.royalBank.entity.User;
import lk.royalBank.repository.UserRepository;
import lk.royalBank.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    BCryptPasswordEncoder encoder;
    @Override
    public void addUser(String userName, UserDTO userDTO) {
        if(!userName.equals(userDTO.getUserName())){
            throw new RuntimeException("ID's Are not Same");
        }

//        String encode = encoder.encode(userDTO.getPassword());
//        userDTO.setPassword(encode);
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        userRepository.save(user);

    }
}
