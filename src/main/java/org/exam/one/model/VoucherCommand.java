package org.exam.one.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public final class VoucherCommand
{
  public record VoucherResponse(
      String code,
      BigDecimal discount,
      @JsonFormat(pattern = "yyyy-MM-dd")
      LocalDate expiry) { }
}
