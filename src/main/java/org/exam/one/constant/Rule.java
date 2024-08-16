package org.exam.one.constant;

import java.math.BigDecimal;
import java.util.function.Function;

import org.exam.one.model.Parcel;

public enum Rule
{
  REJECT("Reject", parcel -> parcel.weight().compareTo(BigDecimal.valueOf(50)) > 0),
  HEAVY_PARCEL("Heavy Parcel", parcel -> parcel.weight().compareTo(BigDecimal.valueOf(10)) > 0),
  SMALL_PARCEL("Small Parcel", parcel -> parcel.volume().compareTo(BigDecimal.valueOf(1500)) < 0),
  MEDIUM_PARCEL("Medium Parcel", parcel -> parcel.volume().compareTo(BigDecimal.valueOf(2500)) < 0),
  LARGE_PARCEL("Large Parcel", parcel -> true);

  private final String name;

  private final Function<Parcel, Boolean> ruleChecker;

  Rule(String name, Function<Parcel, Boolean> ruleChecker) {
    this.name = name;
    this.ruleChecker = ruleChecker;
  }

  public Function<Parcel, Boolean> getRuleChecker() {
    return ruleChecker;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
