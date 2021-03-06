package lazy.fast.code.core.web.exception;

import org.springframework.http.HttpStatus;

import lazy.fast.code.core.web.result.MsgEnum;
import lazy.fast.code.core.web.result.MsgEnumable;
import lazy.fast.code.core.web.result.ResultMsg;

/**
 * 自定义未经过身份认证异常 - HTTP Status 401
 *
 * @author wendell
 */
public class AuthException extends BaseUnCheckException {

    private static final HttpStatus HTTP_STATUS = HttpStatus.UNAUTHORIZED;

    /**
     * {"code":401,"msg":"未经过身份认证"}
     */
    public AuthException() {
        super(HTTP_STATUS, ResultMsg.of(MsgEnum.AUTH_ERROR));
    }

    /**
     * {"code":401,"msg":"${message}"}
     *
     * @param message
     *            异常简要信息
     */
    public AuthException(String message) {
        super(HTTP_STATUS, ResultMsg.of(MsgEnum.AUTH_ERROR.code(), message));
    }

    /**
     * HTTP状态码为401的业务异常类，如果需要自定义返回的code和msg，那必须通过实现MsgEnumable的枚举来定义操作
     *
     * {"code":${msgEnum.code},"msg":"${msgEnum.msg}"}
     *
     * @param msgEnum
     *            消息枚举
     */
    public AuthException(MsgEnumable msgEnum) {
        super(HTTP_STATUS, ResultMsg.of(msgEnum));
    }

}
