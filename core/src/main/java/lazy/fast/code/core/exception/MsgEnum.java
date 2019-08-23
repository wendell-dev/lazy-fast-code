package lazy.fast.code.core.exception;

/**
 * 常用消息枚举类
 *
 * @author wendell
 */
public enum MsgEnum implements MsgEnumable {

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

    private final int code;
    private final String msg;

    MsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String msg() {
        return msg;
    }

}
