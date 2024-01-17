package cn.zhangqin56.common.web;


/**
 * @param code 错误码
 * @param msg  错误提示
 */
public record StatusCode(Integer code, String msg) {
}
