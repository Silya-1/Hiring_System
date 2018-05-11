package com.netcracker.iphs.database.service.claim;

import com.netcracker.iphs.database.model.claim.Claim;
import java.util.List;

public interface ClaimService {

  public Claim saveClaim(Claim claim);

  public void deleteClaim(Long id);

  public Claim getClaimById(Long id);

  public List<Claim> getAllClaims();
}
