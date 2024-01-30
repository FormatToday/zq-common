package cn.zhangqin56.common.enums;

public interface GlobalStatusCodeEnum {
    StatusCodeRecord SUCCESS = new StatusCodeRecord(0, "成功");

    // ========== 客户端错误段 ==========

    StatusCodeRecord BAD_REQUEST = new StatusCodeRecord(400, "请求参数不正确");
    StatusCodeRecord UNAUTHORIZED = new StatusCodeRecord(401, "账号未登录");
    StatusCodeRecord FORBIDDEN = new StatusCodeRecord(403, "没有该操作权限");
    StatusCodeRecord NOT_FOUND = new StatusCodeRecord(404, "请求未找到");
    StatusCodeRecord METHOD_NOT_ALLOWED = new StatusCodeRecord(405, "请求方法不正确");
    StatusCodeRecord LOCKED = new StatusCodeRecord(423, "请求失败，请稍后重试"); // 并发请求，不允许
    StatusCodeRecord TOO_MANY_REQUESTS = new StatusCodeRecord(429, "请求过于频繁，请稍后重试");

    // ========== 服务端错误段 ==========

    StatusCodeRecord INTERNAL_SERVER_ERROR = new StatusCodeRecord(500, "系统异常");
    StatusCodeRecord NOT_IMPLEMENTED = new StatusCodeRecord(501, "功能未实现/未开启");

    // ========== 自定义错误段 ==========
    StatusCodeRecord REPEATED_REQUESTS = new StatusCodeRecord(900, "重复请求，请稍后重试"); // 重复请求
    StatusCodeRecord DEMO_DENY = new StatusCodeRecord(901, "演示模式，禁止写操作");

    StatusCodeRecord UNKNOWN = new StatusCodeRecord(999, "未知错误");

}
