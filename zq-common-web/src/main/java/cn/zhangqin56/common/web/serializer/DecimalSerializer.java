package cn.zhangqin56.common.web.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecimalSerializer extends JsonSerializer<BigDecimal> {

    @Override
    @SneakyThrows
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) {
        if (null != value) {
            value = value.setScale(2, RoundingMode.HALF_UP);
            gen.writeString(value.toString());
        }
    }
}