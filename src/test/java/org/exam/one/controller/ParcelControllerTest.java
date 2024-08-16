package org.exam.one.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import org.exam.one.model.ParcelCommand;
import org.exam.one.model.ParcelCommand.ParcelResponse;
import org.exam.one.model.VoucherCommand.VoucherResponse;
import org.exam.one.service.ParcelService;
import org.exam.one.service.VoucherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ParcelControllerTest
{
  private ParcelController parcelController;

  @BeforeEach
  public void setUp() {
    VoucherService voucherService = mock(VoucherService.class);
    VoucherResponse voucherResponse = new VoucherResponse(
        "VOUCHER-1", BigDecimal.valueOf(1.50), LocalDate.now().plusDays(1));
    when(voucherService.getDiscount()).thenReturn(voucherResponse);

    parcelController = new ParcelController();
    ReflectionTestUtils.setField(parcelController, "parcelService", new ParcelService());
    ReflectionTestUtils.setField(parcelController, "voucherService", voucherService);
  }

  @Test
  public void test_ValidRequest_Success() {
    ParcelCommand.ParcelRequest request = new ParcelCommand.ParcelRequest(
        new BigDecimal("5"), //weight
        new BigDecimal("5"), //height
        new BigDecimal("5"), //width
        new BigDecimal("5") //length
    );

    ResponseEntity<ParcelResponse> response = parcelController.calculate(request);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Small Parcel", Objects.requireNonNull(response.getBody()).description());
    assertEquals(new BigDecimal("1.5"), response.getBody().discount());
    assertEquals(new BigDecimal("2.25"), response.getBody().cost());
  }

  @Test
  public void test_NullParameter_ThenThrowException() {
    ParcelCommand.ParcelRequest request = new ParcelCommand.ParcelRequest(
        null, new BigDecimal("10"), new BigDecimal("15"), new BigDecimal("20"));
    assertThrows(NullPointerException.class, () -> parcelController.calculate(request));
  }
}
