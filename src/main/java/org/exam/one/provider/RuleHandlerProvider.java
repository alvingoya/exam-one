package org.exam.one.provider;

import org.apache.commons.lang3.tuple.Pair;
import org.exam.one.constant.Rule;
import org.exam.one.handler.RuleHandler;
import org.exam.one.handler.impl.HeavyRuleHandler;
import org.exam.one.handler.impl.LargeRuleHandler;
import org.exam.one.handler.impl.MediumRuleHandler;
import org.exam.one.handler.impl.RejectRuleHandler;
import org.exam.one.handler.impl.SmallRuleHandler;
import org.exam.one.model.Parcel;

public final class RuleHandlerProvider
{
  public static Pair<Rule, RuleHandler> getRuleAndHandler(Parcel parcel) {
    Rule rule = Rule.LARGE_PARCEL;
    for (Rule r : Rule.values()) {
      if (r.getRuleChecker().apply(parcel)) {
        rule = r;
        break;
      }
    }
    return Pair.of(rule, getRuleHandler(rule));
  }

  private static RuleHandler getRuleHandler(final Rule rule) {
    return switch (rule) {
      case REJECT -> new RejectRuleHandler();
      case HEAVY_PARCEL -> new HeavyRuleHandler();
      case SMALL_PARCEL -> new SmallRuleHandler();
      case MEDIUM_PARCEL -> new MediumRuleHandler();
      case LARGE_PARCEL -> new LargeRuleHandler();
    };
  }
}
