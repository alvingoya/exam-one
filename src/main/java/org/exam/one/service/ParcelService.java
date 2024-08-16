package org.exam.one.service;

import java.math.BigDecimal;

import org.exam.one.model.Parcel;
import org.exam.one.provider.RuleHandlerProvider;
import org.springframework.stereotype.Service;

@Service
public class ParcelService
{

  public BigDecimal calculateCost(Parcel parcel) {
    return RuleHandlerProvider.getRuleAndHandler(parcel).getRight().calculate(parcel);
  }

}
