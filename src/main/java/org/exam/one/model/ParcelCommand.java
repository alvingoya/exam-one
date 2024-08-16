package org.exam.one.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import org.exam.one.serializer.MoneySerializer;

public final class ParcelCommand
{
  public record ParcelRequest(
      @NotNull(message = "weight required")
      BigDecimal weight,
      @NotNull(message = "height required")
      BigDecimal height,
      @NotNull(message = "width required")
      BigDecimal width,
      @NotNull(message = "length required")
      BigDecimal length
  ) { }

  public record ParcelResponse(
      String description,
      @JsonSerialize(using = MoneySerializer.class)
      BigDecimal discount,
      @JsonSerialize(using = MoneySerializer.class)
      BigDecimal cost
  ) { }

  public static Parcel toParcel(ParcelRequest request) {
    return new Parcel(
        request.weight(),
        request.height(),
        request.width(),
        request.length());
  }
}
