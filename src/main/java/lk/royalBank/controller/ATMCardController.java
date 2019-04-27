package lk.royalBank.controller;

import lk.royalBank.dto.ATMcardDTO;
import lk.royalBank.dto.BankAccountDTO;
import lk.royalBank.service.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/atmcards")
public class ATMCardController {

    @Autowired
    private ATMService atmService;



    @PostMapping
    public void save(@RequestBody ATMcardDTO atMcardDTO){

        System.out.println(atMcardDTO);
        atmService.addATM(atMcardDTO);
    }

    @GetMapping(value = "login/{pin}")
    public BankAccountDTO atmLogin(@PathVariable("pin") String pin){
        System.out.println(pin);
//        System.out.println(pin);
//        return "hello";
        return atmService.loginATM(pin);
    }

    @GetMapping(value = "/reqDeactive/{pin}/{nic}")
    public String requestDeactiveATM (@PathVariable String pin,@PathVariable String nic) throws MessagingException {
        return atmService.requestDeactiveATM(pin,nic);
    }

    @DeleteMapping(value = "/{pin}")
    public void remove(@PathVariable String pin){
        atmService.removeATM(pin);
    }
}
