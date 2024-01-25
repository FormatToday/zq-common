package cn.zhangqin56.common.utils;

import cn.hutool.core.util.ObjectUtil;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 数据抽取工具类
 *
 * @author ZhangQin
 */
@UtilityClass
public class ExtraDataUtils {
    private <T> Optional<T> wrapperData(T data) {
        return ObjectUtil.isNull(data) ? Optional.empty() : Optional.of(data);
    }

    /**
     * 获取 T 的 R 对应的字段，如果 T 为 null，则返回 null
     */
    public <T, R> R getData(T data, Function<T, R> ref) {
        return wrapperData(data)
                .map(ref)
                .orElse(null);
    }

    public <E extends Enum<E>> E getEnumBy(Class<E> enumClass, Predicate<? super E> predicate) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }
}
