package lk.royalBank.service;

import lk.royalBank.dto.BranchDTO;

import java.util.List;

public interface BranchService  {


    List<BranchDTO> getAll();

    BranchDTO findByID(String branchID);

}
