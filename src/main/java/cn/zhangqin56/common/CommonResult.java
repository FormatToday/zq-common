package cn.zhangqin56.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> implements Serializable {
    private static final StatusCode DEFAULT_SUCCESS_STATUS = GlobalStatusCode.SUCCESS;
    private static final StatusCode DEFAULT_ERROR_STATUS = GlobalStatusCode.INTERNAL_SERVER_ERROR;

    private Integer code;
    private T data;
    private String msg;

    private static <T> CommonResult<T> getResult(T data, StatusCode status) {
        return CommonResult.<T>builder()
                .data(data)
                .msg(status.msg())
                .code(status.code())
                .build();
    }

    public static <T> CommonResult<T> success() {
        return success(null);
    }

    public static <T> CommonResult<T> success(T data) {
        return getResult(data, DEFAULT_SUCCESS_STATUS);
    }


    public static <T> CommonResult<T> success(T data, StatusCode statusCode) {
        return getResult(data, statusCode);
    }

    public static <T> CommonResult<T> error() {
        return error(null);
    }

    public static <T> CommonResult<T> error(T data) {
        return getResult(data, DEFAULT_ERROR_STATUS);
    }

    public static <T> CommonResult<T> error(T data, StatusCode statusCode) {
        return getResult(data, statusCode);
    }

    public static <T> CommonResult<T> simpleReturn(boolean b) {
        return b ? success() : error();
    }

}