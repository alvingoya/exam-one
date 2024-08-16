package org.exam.one.handler.impl;

import java.math.BigDecimal;

import org.exam.one.handler.RuleHandler;
import org.exam.one.model.Parcel;

public class HeavyRuleHandler
    implements RuleHandler
{
  @Override
  public BigDecimal calculate(final Parcel parcel) {
    return parcel.weight().multiply(BigDecimal.valueOf(20));
  }
}
