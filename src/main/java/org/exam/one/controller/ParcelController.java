package org.exam.one.controller;

import java.math.BigDecimal;

import jakarta.annotation.Resource;
import org.exam.one.constant.Rule;
import org.exam.one.model.Parcel;
import org.exam.one.model.ParcelCommand;
import org.exam.one.model.ParcelCommand.ParcelResponse;
import org.exam.one.model.VoucherCommand.VoucherResponse;
import org.exam.one.provider.RuleHandlerProvider;
import org.exam.one.service.ParcelService;
import org.exam.one.service.VoucherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parcel")
public class ParcelController
{
  @Resource
  private ParcelService parcelService;

  @Resource
  private VoucherService voucherService;

  @GetMapping(value = "calculate", produces = "application/json")
  public ResponseEntity<ParcelCommand.ParcelResponse> calculate(ParcelCommand.ParcelRequest request) {
    Parcel parcel = ParcelCommand.toParcel(request);
    BigDecimal calculateCost = parcelService.calculateCost(parcel);
    VoucherResponse voucherResponse = voucherService.getDiscount();
    Rule rule = RuleHandlerProvider.getRuleAndHandler(parcel).getLeft();
    return ResponseEntity.ok(new ParcelResponse(
        rule.toString(),
        voucherResponse.discount(),
        calculateCost.subtract(voucherResponse.discount())));
  }
}
