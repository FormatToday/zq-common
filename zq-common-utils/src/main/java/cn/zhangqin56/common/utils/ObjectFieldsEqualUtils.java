package cn.zhangqin56.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 对象字段比较相等工具类
 *
 * @author ZhangQin
 */
@Slf4j
@UtilityClass
public class ObjectFieldsEqualUtils {

    /**
     * 是否每个字段的值都是相同的，可添加例外字段
     *
     * @author ZhangQin
     */
    public <T> boolean isSameDataExceptSomeFields(T obj1, T obj2, List<String> exceptFieldNames) {
        logger.info("\n");
        logger.debug("exceptField:{}", exceptFieldNames);
        // 获取对象的所有字段名
        List<Field> fields = CollUtil.toList(ReflectUtil.getFields(obj1.getClass()));

        if (CollUtil.isNotEmpty(exceptFieldNames)) {
            fields = fields.stream()
                    .filter(i -> !exceptFieldNames.contains(i.getName()))
                    .collect(Collectors.toList());
        }

        logger.debug("CheckFields:{}", fields.stream().map(Field::getName).collect(Collectors.toList()));
        boolean res = true;

        // 遍历字段名进行比较
        for (Field field : fields) {
            Object value1 = ReflectUtil.getFieldValue(obj1, field);
            Object value2 = ReflectUtil.getFieldValue(obj2, field);

            if (ObjectUtil.equal(value1, value2)) {
                logger.debug("字段相同。字段：{}，值：{}", field.getName(), value1);
            } else {
                logger.info("字段值不同。字段：{}，值：{}/{}", field.getName(), value1, value2);
                res = false;
            }
        }

        return res;
    }

    /**
     * 是否每个字段的值都是相同的，可添加例外字段
     *
     * @author ZhangQin
     */
    public <T> boolean isSameDataExceptSomeFields(List<T> objs, List<String> exceptFieldNames) {
        if (CollUtil.isEmpty(objs) || objs.size() < 2) {
            return false;
        }
        T obj1 = objs.get(0);
        boolean ret = true;
        for (int i = 1; i < objs.size(); i++) {
            ret = ret && isSameDataExceptSomeFields(obj1, objs.get(i), exceptFieldNames);
        }
        return ret;
    }

    /**
     * 是否每个字段的值都是相同的
     *
     * @author ZhangQin
     */
    public <T> boolean isSameData(T obj1, T obj2) {
        return isSameDataExceptSomeFields(obj1, obj2, null);
    }

    /**
     * 是否每个字段的值都是相同的
     *
     * @author ZhangQin
     */
    public <T> boolean isSameData(List<T> objs) {
        return isSameDataExceptSomeFields(objs, null);
    }

    /**
     * 是否每个字段的值都是相同的
     *
     * @author ZhangQin
     */
    public <T> boolean isSameData(T... objs) {
        return isSameDataExceptSomeFields(CollUtil.toList(objs), null);
    }
}
