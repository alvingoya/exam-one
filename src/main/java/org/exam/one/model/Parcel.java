package org.exam.one.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record Parcel(
    @NotNull(message = "weight required") BigDecimal weight,
    @NotNull(message = "height required") BigDecimal height,
    @NotNull(message = "width required") BigDecimal width,
    @NotNull(message = "length required") BigDecimal length) {

  public BigDecimal volume() {
    return height.multiply(width).multiply(length);
  }

}
