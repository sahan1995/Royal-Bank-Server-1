package lk.royalBank.service.impl;

import lk.royalBank.dto.BranchDTO;
import lk.royalBank.entity.Branch;
import lk.royalBank.repository.BranchRepository;
import lk.royalBank.service.BranchService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public List<BranchDTO> getAll() {
        List<Branch> branches = branchRepository.findAll();
        if(branches.isEmpty()){
            return null;
        }
        List<BranchDTO> branchDTOS = new ArrayList<>();
        branches.forEach(branch -> {
            BranchDTO branchDTO = new BranchDTO();
            BeanUtils.copyProperties(branch,branchDTO);
            branchDTOS.add(branchDTO);
        });

        return branchDTOS;
    }

    @Override
    public BranchDTO findByID(String branchID) {
        int branchIDI = Integer.parseInt(branchID);
      if(  branchRepository.findById(branchIDI).isPresent()){
          Branch branch = branchRepository.findById(branchIDI).get();
          BranchDTO branchDTO = new BranchDTO();
          BeanUtils.copyProperties(branch,branchDTO);
          return branchDTO;
      }
      return null;
    }
}
