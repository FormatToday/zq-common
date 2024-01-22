package cn.zhangqin56.common.utils;

import cn.hutool.core.util.ObjectUtil;
import lombok.experimental.UtilityClass;

import java.util.Optional;
import java.util.function.Function;

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

    public <T> T getData(T data, Function<T, T> ref) {
        return wrapperData(data)
                .map(ref)
                .orElse(null);
    }
}
