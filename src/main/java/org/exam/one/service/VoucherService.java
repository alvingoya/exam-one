package org.exam.one.service;

import jakarta.annotation.Resource;
import org.exam.one.model.VoucherCommand.VoucherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class VoucherService
{
  @Resource
  private RestClient restClient;

  @Value("${exam-one.voucher.url}")
  private String voucherUrl;

  public VoucherResponse getDiscount() {
    return restClient.get()
        .uri(buildStubUri())
        .retrieve()
        .body(VoucherResponse.class);
  }

  private String buildStubUri() {
    return UriComponentsBuilder.fromHttpUrl(voucherUrl)
        .path("GFI")
        .queryParam("key", "apikey")
        .build().toUriString();
  }
}
