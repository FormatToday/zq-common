package cn.zhangqin56.common.web;

public interface GlobalStatusCode {
    StatusCode SUCCESS = new StatusCode(0, "成功");

    // ========== 客户端错误段 ==========

    StatusCode BAD_REQUEST = new StatusCode(400, "请求参数不正确");
    StatusCode UNAUTHORIZED = new StatusCode(401, "账号未登录");
    StatusCode FORBIDDEN = new StatusCode(403, "没有该操作权限");
    StatusCode NOT_FOUND = new StatusCode(404, "请求未找到");
    StatusCode METHOD_NOT_ALLOWED = new StatusCode(405, "请求方法不正确");
    StatusCode LOCKED = new StatusCode(423, "请求失败，请稍后重试"); // 并发请求，不允许
    StatusCode TOO_MANY_REQUESTS = new StatusCode(429, "请求过于频繁，请稍后重试");

    // ========== 服务端错误段 ==========

    StatusCode INTERNAL_SERVER_ERROR = new StatusCode(500, "系统异常");
    StatusCode NOT_IMPLEMENTED = new StatusCode(501, "功能未实现/未开启");

    // ========== 自定义错误段 ==========
    StatusCode REPEATED_REQUESTS = new StatusCode(900, "重复请求，请稍后重试"); // 重复请求
    StatusCode DEMO_DENY = new StatusCode(901, "演示模式，禁止写操作");

    StatusCode UNKNOWN = new StatusCode(999, "未知错误");

}
