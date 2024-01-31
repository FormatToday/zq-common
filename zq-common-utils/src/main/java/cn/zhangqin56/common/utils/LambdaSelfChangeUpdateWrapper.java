package cn.zhangqin56.common.utils;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import lombok.extern.slf4j.Slf4j;

/**
 * 指定列自增、自减
 *
 * @author ZhangQin
 */
@Slf4j
public class LambdaSelfChangeUpdateWrapper<T> extends LambdaUpdateWrapper<T> {
    public LambdaSelfChangeUpdateWrapper(Class<T> entityClass) {
        super(entityClass);
    }

    /**
     * 指定列自增
     *
     * @param columns 列引用
     * @param value   增长值
     */
    public LambdaSelfChangeUpdateWrapper<T> increase(SFunction<T, ?> columns, Object value) {
        String colStr = super.columnToString(columns);
        String sql = StrUtil.format("{} = {} + {}", colStr, colStr, formatSqlMaybeWithParam("{0}", value));
        logger.debug("sql:{}", sql);
        setSql(sql);

        return this;
    }

    /**
     * 指定列自增1
     *
     * @param columns 列引用
     */
    public LambdaSelfChangeUpdateWrapper<T> increase(SFunction<T, ?> columns) {
        return increase(columns, 1);
    }

    /**
     * 指定列自减
     *
     * @param columns 列引用
     * @param value   减少值
     */
    public LambdaSelfChangeUpdateWrapper<T> decrease(SFunction<T, ?> columns, Object value) {
        String colStr = super.columnToString(columns);
        String sql = StrUtil.format("{} = {} - {}", colStr, colStr, formatSqlMaybeWithParam("{0}", value));
        logger.debug("sql:{}", sql);
        setSql(sql);

        return this;
    }

    /**
     * 指定列自减1
     *
     * @param columns 列引用
     */
    public LambdaSelfChangeUpdateWrapper<T> decrease(SFunction<T, ?> columns) {
        return decrease(columns, 1);
    }

}
