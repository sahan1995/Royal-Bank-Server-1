package lk.royalBank.controller;

import lk.royalBank.dto.BranchDTO;
import lk.royalBank.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/branches")
public class BrachController {

    @Autowired
    private BranchService branchService;


    @GetMapping
    public List<BranchDTO> findAll(){
      return branchService.getAll();
    }


    @GetMapping(value = "/{ID}")
    public BranchDTO findByID(@PathVariable("ID") String Id){
        return branchService.findByID(Id);
    }



}
