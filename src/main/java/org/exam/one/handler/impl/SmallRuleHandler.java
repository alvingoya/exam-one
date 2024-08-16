package org.exam.one.handler.impl;

import java.math.BigDecimal;

import org.exam.one.handler.RuleHandler;
import org.exam.one.model.Parcel;

public class SmallRuleHandler
    implements RuleHandler
{
  @Override
  public BigDecimal calculate(final Parcel parcel) {
    return parcel.volume().multiply(BigDecimal.valueOf(0.03));
  }
}
