package lk.royalBank.controller;

import lk.royalBank.dto.DepositDTO;
import lk.royalBank.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/deposits")
public class DepositController {

    @Autowired
    DepositService depositService;
    @PostMapping
    public void depostMoney(@RequestBody DepositDTO depositDTO){
        depositService.depositMoney(depositDTO);

    }



}
