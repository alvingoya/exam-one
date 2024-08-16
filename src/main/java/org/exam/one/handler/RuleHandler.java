package org.exam.one.handler;

import java.math.BigDecimal;

import org.exam.one.model.Parcel;

public interface RuleHandler
{
  BigDecimal calculate(Parcel parcel);
}
