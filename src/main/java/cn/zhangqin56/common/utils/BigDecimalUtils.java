package cn.zhangqin56.common.utils;

import cn.hutool.core.collection.CollUtil;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * BigDecimal 工具类
 *
 * @author ZhangQin
 */
@UtilityClass
public class BigDecimalUtils {

    public final int DEFAULT_DIVIDE_SCALE = 5;
    public final int DEFAULT_MULTIPLY_SCALE = 2;

    /**
     * 计算传入的BigDecimal数组中所有元素的总和。
     *
     * @param numbers BigDecimal数组，包含要相加的数字
     * @return 返回BigDecimal类型的结果，即所有数字的总和
     */
    public BigDecimal sum(BigDecimal... numbers) {
        return sum(Arrays.asList(numbers));
    }


    /**
     * 计算BigDecimal类型列表中所有非空数字的总和。
     *
     * @param numbers 包含BigDecimal类型数字的列表
     * @return 所有非空数字的总和，以BigDecimal类型表示
     */
    public BigDecimal sum(List<BigDecimal> numbers) {
        if (CollUtil.isEmpty(numbers)) {
            return BigDecimal.ZERO;
        }
        return numbers.stream()
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    /**
     * 对list中对象的某个BigDecimal字段求和
     */
    public <T> BigDecimal sum(List<T> objsContainsBigDecimal, Function<T, BigDecimal> ref) {
        return objsContainsBigDecimal.stream()
                .filter(Objects::nonNull)
                .map(ref)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * 对list中对象的某个BigDecimal字段求和
     */
    public <T> BigDecimal sum(List<T> objsContainsBigDecimal, Predicate<T> filter, Function<T, BigDecimal> ref) {
        if (CollUtil.isEmpty(objsContainsBigDecimal)) {
            return BigDecimal.ZERO;
        }
        return objsContainsBigDecimal.stream()
                .filter(Objects::nonNull)
                .filter(filter)
                .map(ref)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * 大于
     */
    public boolean gt(BigDecimal v1, BigDecimal v2) {
        return v1.compareTo(v2) > 0;
    }

    /**
     * 大于0
     */
    public boolean gt0(BigDecimal v) {
        return gt(v, BigDecimal.ZERO);
    }

    /**
     * 大于等于
     */
    public boolean ge(BigDecimal v1, BigDecimal v2) {
        return v1.compareTo(v2) >= 0;
    }

    /**
     * 大于等于0
     */
    public boolean ge0(BigDecimal v) {
        return ge(v, BigDecimal.ZERO);
    }

    /**
     * 小于
     */
    public boolean lt(BigDecimal v1, BigDecimal v2) {
        return v1.compareTo(v2) < 0;
    }

    /**
     * 小于0
     */
    public boolean lt0(BigDecimal v) {
        return lt(v, BigDecimal.ZERO);
    }

    /**
     * 小于等于
     */
    public boolean le(BigDecimal v1, BigDecimal v2) {
        return v1.compareTo(v2) <= 0;
    }

    /**
     * 小于等于0
     */
    public boolean le0(BigDecimal v) {
        return le(v, BigDecimal.ZERO);
    }

    /**
     * 不等于
     */
    public boolean ne(BigDecimal v1, BigDecimal v2) {
        return v1.compareTo(v2) != 0;
    }

    /**
     * 不等于0
     */
    public boolean ne0(BigDecimal v) {
        return ne(v, BigDecimal.ZERO);
    }

    /**
     * 等于
     */
    public boolean eq(BigDecimal v1, BigDecimal v2) {
        return v1.compareTo(v2) == 0;
    }

    /**
     * 等于0
     */
    public boolean eq0(BigDecimal v) {
        return eq(v, BigDecimal.ZERO);
    }


    /**
     * 封装BigDecimal对象
     *
     * @param value 要封装的BigDecimal对象
     * @return 如果value为null，则返回BigDecimal.ZERO；否则返回value
     */
    public BigDecimal wrapBigDecimal(BigDecimal value) {
        return Optional.ofNullable(value).orElse(BigDecimal.ZERO);
    }

    /**
     * 返回入参的负数值，负数仍是负数
     */
    public BigDecimal negate(BigDecimal value) {
        return wrapBigDecimal(value).abs().negate();
    }

    public BigDecimal cal(BigDecimal v1, BigDecimal v2, CalculateType type) {
        return switch (type) {
            case ADD -> wrapBigDecimal(v1).add(wrapBigDecimal(v2));
            case SUBTRACT -> wrapBigDecimal(v1).subtract(wrapBigDecimal(v2));
            case MULTIPLY, MULTIPLY_CEILING ->
                    wrapBigDecimal(v1).multiply(wrapBigDecimal(v2)).setScale(DEFAULT_DIVIDE_SCALE, RoundingMode.CEILING);
            case MULTIPLY_UP ->
                    wrapBigDecimal(v1).multiply(wrapBigDecimal(v2)).setScale(DEFAULT_MULTIPLY_SCALE, RoundingMode.UP);
            case MULTIPLY_DOWN ->
                    wrapBigDecimal(v1).multiply(wrapBigDecimal(v2)).setScale(DEFAULT_MULTIPLY_SCALE, RoundingMode.DOWN);
            case MULTIPLY_FLOOR ->
                    wrapBigDecimal(v1).multiply(wrapBigDecimal(v2)).setScale(DEFAULT_MULTIPLY_SCALE, RoundingMode.FLOOR);
            case MULTIPLY_HALF_UP ->
                    wrapBigDecimal(v1).multiply(wrapBigDecimal(v2)).setScale(DEFAULT_MULTIPLY_SCALE, RoundingMode.HALF_UP);
            case MULTIPLY_HALF_DOWN ->
                    wrapBigDecimal(v1).multiply(wrapBigDecimal(v2)).setScale(DEFAULT_MULTIPLY_SCALE, RoundingMode.HALF_DOWN);
            case MULTIPLY_HALF_EVEN ->
                    wrapBigDecimal(v1).multiply(wrapBigDecimal(v2)).setScale(DEFAULT_MULTIPLY_SCALE, RoundingMode.HALF_EVEN);
            case MULTIPLY_UNNECESSARY ->
                    wrapBigDecimal(v1).multiply(wrapBigDecimal(v2)).setScale(DEFAULT_MULTIPLY_SCALE, RoundingMode.UNNECESSARY);
            case DIVIDE_UP -> wrapBigDecimal(v1).divide(v2, DEFAULT_DIVIDE_SCALE, RoundingMode.UP);
            case DIVIDE_DOWN -> wrapBigDecimal(v1).divide(v2, DEFAULT_DIVIDE_SCALE, RoundingMode.DOWN);
            case DIVIDE_CEILING -> wrapBigDecimal(v1).divide(v2, DEFAULT_DIVIDE_SCALE, RoundingMode.CEILING);
            case DIVIDE_FLOOR -> wrapBigDecimal(v1).divide(v2, DEFAULT_DIVIDE_SCALE, RoundingMode.FLOOR);
            case DIVIDE_HALF_UP -> wrapBigDecimal(v1).divide(v2, DEFAULT_DIVIDE_SCALE, RoundingMode.HALF_UP);
            case DIVIDE_HALF_DOWN -> wrapBigDecimal(v1).divide(v2, DEFAULT_DIVIDE_SCALE, RoundingMode.HALF_DOWN);
            case DIVIDE_HALF_EVEN -> wrapBigDecimal(v1).divide(v2, DEFAULT_DIVIDE_SCALE, RoundingMode.HALF_EVEN);
            case DIVIDE_UNNECESSARY -> wrapBigDecimal(v1).divide(v2, DEFAULT_DIVIDE_SCALE, RoundingMode.UNNECESSARY);
        };
    }

    public enum CalculateType {
        ADD,
        SUBTRACT,
        MULTIPLY,
        MULTIPLY_UP,
        MULTIPLY_DOWN,
        MULTIPLY_CEILING,
        MULTIPLY_FLOOR,
        MULTIPLY_HALF_UP,
        MULTIPLY_HALF_DOWN,
        MULTIPLY_HALF_EVEN,
        MULTIPLY_UNNECESSARY,
        DIVIDE_UP,
        DIVIDE_DOWN,
        DIVIDE_CEILING,
        DIVIDE_FLOOR,
        DIVIDE_HALF_UP,
        DIVIDE_HALF_DOWN,
        DIVIDE_HALF_EVEN,
        DIVIDE_UNNECESSARY,
    }
}