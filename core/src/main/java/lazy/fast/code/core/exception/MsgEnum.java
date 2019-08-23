package lazy.fast.code.core.exception;

import lombok.Getter;

/**
 * 基础常用消息枚举类
 *
 * @author wendell
 */
@Getter
public enum MsgEnum {

    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 操作失败
     */
    FAIL(400, "操作失败"),
    /**
     * 服务器异常,请稍后再试
     */
    ERROR(500, "服务器异常,请稍后再试"),
    /**
     * 未经过身份认证
     */
    AUTH_ERROR(401, "未经过身份认证"),
    /**
     * 无访问权限
     */
    FORBIDDEN(403, "无访问权限"),
    /**
     * 资源不存在
     */
    NOT_FOUND(404, "资源不存在"),
    /**
     * RPC或与其他项目通信调用异常，外部服务异常
     */
    RPC_ERROR(503, "外部服务异常");

    private int code;
    private String msg;

    MsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
