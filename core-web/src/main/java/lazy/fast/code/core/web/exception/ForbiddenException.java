package lazy.fast.code.core.web.exception;

import org.springframework.http.HttpStatus;

import lazy.fast.code.core.web.result.MsgEnum;
import lazy.fast.code.core.web.result.MsgEnumable;
import lazy.fast.code.core.web.result.ResultMsg;

/**
 * 访问被禁止异常 - HTTP Status 403
 *
 * @author wendell
 */
public class ForbiddenException extends BaseUnCheckException {

    private static final HttpStatus HTTP_STATUS = HttpStatus.FORBIDDEN;

    /**
     * {"code":403,"msg":"无访问权限"}
     */
    public ForbiddenException() {
        super(HTTP_STATUS, ResultMsg.of(MsgEnum.FORBIDDEN));
    }

    /**
     * {"code":403,"msg":"${message}"}
     *
     * @param message
     *            异常简要信息
     */
    public ForbiddenException(String message) {
        super(HTTP_STATUS, ResultMsg.of(MsgEnum.FORBIDDEN.code(), message));
    }

    /**
     * HTTP状态码为403的业务异常类，如果需要自定义返回的code和msg，那必须通过实现MsgEnumable的枚举来定义操作
     *
     * {"code":${msgEnum.code},"msg":"${msgEnum.msg}"}
     *
     * @param msgEnum
     *            消息枚举
     */
    public ForbiddenException(MsgEnumable msgEnum) {
        super(HTTP_STATUS, ResultMsg.of(msgEnum));
    }

}
