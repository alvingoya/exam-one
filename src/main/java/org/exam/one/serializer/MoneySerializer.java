package org.exam.one.serializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class MoneySerializer
    extends JsonSerializer<BigDecimal>
{
  @Override
  public void serialize(
      final BigDecimal value,
      final JsonGenerator jsonGenerator,
      final SerializerProvider serializerProvider)
      throws IOException
  {
    jsonGenerator.writeNumber(value.setScale(2, RoundingMode.HALF_UP));
  }
}
