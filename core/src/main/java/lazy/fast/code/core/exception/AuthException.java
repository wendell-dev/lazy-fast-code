package lazy.fast.code.core.exception;

/**
 * 自定义未经过身份认证异常 - HTTP Status 401
 *
 * @author wendell
 */
public class AuthException extends RuntimeException {

    /**
     * {"code":401,"msg":"未经过身份认证"}
     */
    public AuthException() {
        super(ErrorMsg.of(MsgEnum.AUTH_ERROR));
    }

    /**
     * {"code":401,"msg":"${message}"}
     *
     * @param message
     *            异常简要信息
     */
    public AuthException(String message) {
        super(ErrorMsg.of(MsgEnum.AUTH_ERROR.getCode(), message));
    }

    /**
     * HTTP状态码为401的业务异常类，自定义返回的code和msg
     *
     * {"code":code,"msg":msg}
     *
     * @param code
     *            异常code
     * @param msg
     *            异常简要信息
     */
    public AuthException(int code, String msg) {
        super(ErrorMsg.of(code, msg));
    }

}
