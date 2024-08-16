package org.exam.one.handler.impl;

import java.math.BigDecimal;

import org.exam.one.model.Parcel;
import org.exam.one.handler.RuleHandler;

public class MediumRuleHandler
    implements RuleHandler
{
  @Override
  public BigDecimal calculate(final Parcel parcel) {
    return parcel.volume().multiply(BigDecimal.valueOf(0.04));
  }
}
