package org.exam.one.provider;

import java.math.BigDecimal;

import org.apache.commons.lang3.tuple.Pair;
import org.exam.one.constant.Rule;
import org.exam.one.handler.RuleHandler;
import org.exam.one.handler.impl.HeavyRuleHandler;
import org.exam.one.handler.impl.LargeRuleHandler;
import org.exam.one.handler.impl.MediumRuleHandler;
import org.exam.one.handler.impl.RejectRuleHandler;
import org.exam.one.handler.impl.SmallRuleHandler;
import org.exam.one.model.Parcel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuleHandlerProviderTest
{
  @Test
  void test_ParcelIsNull_thenException() {
    assertThrows(NullPointerException.class, () -> RuleHandlerProvider.getRuleAndHandler(null));
  }

  @Test
  void test_ParcelParamNull_thenException() {
    assertThrows(NullPointerException.class, () -> RuleHandlerProvider.getRuleAndHandler(new Parcel(BigDecimal.ONE,null,null,null)));
  }

  @Test
  void test_Parcel_thenReject() {
    Parcel parcel = new Parcel(
        new BigDecimal("50.1"), //weight
        new BigDecimal("1"), //height
        new BigDecimal("1"), //width
        new BigDecimal("1") //length
    );
    Pair<Rule, RuleHandler> handlerPair = RuleHandlerProvider.getRuleAndHandler(parcel);
    assertEquals(Rule.REJECT, handlerPair.getLeft());
    assertEquals(RejectRuleHandler.class, handlerPair.getRight().getClass());
  }


  @Test
  void test_Parcel_thenHeavy() {
    Parcel parcel = new Parcel(
        new BigDecimal("10.1"), //weight
        new BigDecimal("1"), //height
        new BigDecimal("1"), //width
        new BigDecimal("1") //length
    );
    Pair<Rule, RuleHandler> handlerPair = RuleHandlerProvider.getRuleAndHandler(parcel);
    assertEquals(Rule.HEAVY_PARCEL, handlerPair.getLeft());
    assertEquals(HeavyRuleHandler.class, handlerPair.getRight().getClass());
  }

  @Test
  void test_Parcel_thenSmall() {
    Parcel parcel = new Parcel(
        new BigDecimal("1"), //weight
        new BigDecimal("10"), //height
        new BigDecimal("10"), //width
        new BigDecimal("10") //length
    );
    Pair<Rule, RuleHandler> handlerPair = RuleHandlerProvider.getRuleAndHandler(parcel);
    assertEquals(Rule.SMALL_PARCEL, handlerPair.getLeft());
    assertEquals(SmallRuleHandler.class, handlerPair.getRight().getClass());
  }

  @Test
  void test_Parcel_thenMedium() {
    Parcel parcel = new Parcel(
        new BigDecimal("1"), //weight
        new BigDecimal("10"), //height
        new BigDecimal("10"), //width
        new BigDecimal("24") //length
    );
    Pair<Rule, RuleHandler> handlerPair = RuleHandlerProvider.getRuleAndHandler(parcel);
    assertEquals(Rule.MEDIUM_PARCEL, handlerPair.getLeft());
    assertEquals(MediumRuleHandler.class, handlerPair.getRight().getClass());
  }

  @Test
  void test_Parcel_thenLarge() {
    Parcel parcel = new Parcel(
        new BigDecimal("1"), //weight
        new BigDecimal("10"), //height
        new BigDecimal("10"), //width
        new BigDecimal("25") //length
    );
    Pair<Rule, RuleHandler> handlerPair = RuleHandlerProvider.getRuleAndHandler(parcel);
    assertEquals(Rule.LARGE_PARCEL, handlerPair.getLeft());
    assertEquals(LargeRuleHandler.class, handlerPair.getRight().getClass());
  }
}
