package org.exam.one.handler.impl;

import java.math.BigDecimal;

import org.exam.one.exception.ParcelException;
import org.exam.one.handler.RuleHandler;
import org.exam.one.model.Parcel;

public class RejectRuleHandler
    implements RuleHandler
{
  @Override
  public BigDecimal calculate(final Parcel parcel) {
    throw new ParcelException("Weight exceeded limit of 50kg");
  }
}
