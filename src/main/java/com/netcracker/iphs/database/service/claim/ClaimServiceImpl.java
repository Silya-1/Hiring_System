package com.netcracker.iphs.database.service.claim;

import com.netcracker.iphs.database.model.claim.Claim;
import com.netcracker.iphs.database.repositories.claim.ClaimRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClaimServiceImpl implements ClaimService {

  private ClaimRepository claimRepository;

  @Autowired
  public ClaimServiceImpl(ClaimRepository claimRepository){
    this.claimRepository = claimRepository;
  }

  @Override
  public Claim saveClaim(Claim claim) {
    return claimRepository.save(claim);
  }

  @Override
  public void deleteClaim(Long id) {
    claimRepository.delete(id);
  }

  @Override
  public Claim getClaimById(Long id) {
    return claimRepository.findOne(id);
  }

  @Override
  public List<Claim> getAllClaims() {
    List<Claim> result = new ArrayList<>();
    claimRepository.findAll().forEach(result::add);
    return result;
  }
}
