package lazy.fast.code.core.exception;

/**
 * 自定义资源不存在异常类 - HTTP Status 404
 * 
 * @author wendell
 */
public class NotFoundException extends RuntimeException {

    /**
     * {"code":404,"msg":"资源不存在"}
     */
    public NotFoundException() {
        super(ErrorMsg.of(MsgEnum.NOT_FOUND));
    }

    /**
     * {"code":404,"msg":"${message}"}
     *
     * @param message
     *            异常简要信息
     */
    public NotFoundException(String message) {
        super(ErrorMsg.of(MsgEnum.NOT_FOUND.code(), message));
    }

    /**
     * HTTP状态码为404的业务异常类，如果需要自定义返回的code和msg，那必须通过实现MsgEnumable的枚举来定义操作
     *
     * {"code":${msgEnum.code},"msg":"${msgEnum.msg}"}
     *
     * @param msgEnum
     *            消息枚举
     */
    public NotFoundException(MsgEnumable msgEnum) {
        super(ErrorMsg.of(msgEnum));
    }

}
