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
        super(ErrorMsg.of(MsgEnum.NOT_FOUND.getCode(), message));
    }

    /**
     * HTTP状态码为404的业务异常类，自定义返回的code和msg
     *
     * {"code":code,"msg":msg}
     *
     * @param code
     *            异常code
     * @param msg
     *            异常简要信息
     */
    public NotFoundException(int code, String msg) {
        super(ErrorMsg.of(code, msg));
    }

}
