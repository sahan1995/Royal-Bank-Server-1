package lk.royalBank.controller;

import lk.royalBank.dto.BankAccountDTO;
import lk.royalBank.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/account")
public class BankAccountController {

    @Autowired
    BankAccountService bankAccountService;


    @PostMapping(value = "/{id}")
    public void createAccount(@PathVariable("id") String accountNumber, @RequestBody BankAccountDTO bankAccountDTO){
        bankAccountService.addBankAccount(accountNumber,bankAccountDTO);
    }

    @GetMapping(path = "/checkBalance")
    public double checkBalance(@RequestParam("accountNumber")String accountNumber){
        return bankAccountService.checkBalance(accountNumber);
    }

    @GetMapping
    public void test(){
        throw new RuntimeException("Run Time");
    }
}
